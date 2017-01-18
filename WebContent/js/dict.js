var URLS = {
    GET_PAGE_DICT_TYPE : contextPath + "/dictType/getPage",
    GET_PAGE_DICT_VALUE : contextPath + "/dict/getPage",
    DELETE_DICT_TYPE : contextPath + "/dictType/delete",
}

var dictTypeParams = {};
var dictParams = {};

function delDictType(typeId) {
    var params = {
        typeId : typeId
    };

    $.post(URLS.DELETE_DICT_TYPE, params, function(data) {
        if (checkRespCodeSucc(data)) {
            bootstrapTableRefreshCurrentPage('#dict-type-table');
            return;
        }

        new Message().show(data.respDesc);
    });
}

function initDictTypeTable() {
    $('#dict-type-table').bootstrapTable({
        url : URLS.GET_PAGE_DICT_TYPE,
        method : 'POST',
        contentType : "application/x-www-form-urlencoded",
        cache : false,
        striped : true,
        pagination : true,
        showRefresh : true,
        showToggle : true,
        smartDisplay : true,
        height : 343,
        minimumCountColumns : 1,
        pageSize : 5,
        clickToSelect : true,
        showExport : true,
        pageList : [ 5, 10, 25, 50, 100, 'All' ],
        idField : 'id',
        singleSelect : true,
        toolbar : '#dict-type-toolbar',
        sidePagination : 'server',
        dataField : 'records',
        queryParams : function(e) {
            var page = {
                currentPage : (e.offset / e.limit) + 1,
                pageSize : e.limit,
            }

            return $.extend(dictTypeParams, page);
        },
        columns : [ {
            checkbox : true,
        }, {
            field : 'code',
            title : '字典代码',
        }, {
            field : 'name',
            title : '字典名称',
        }, {
            field : 'remark',
            title : '备注',
        } ],
        onCheck : function(row) {
            $('#btn-edit-dict-type').removeAttr('disabled');
            $('#btn-del-dict-type').removeAttr('disabled');

            $('#btn-add-dict-value').removeAttr('disabled');

            if ($('#dict-value-table').bootstrapTable('getOptions').pageNumber > 1) {
                $('#dict-value-table').bootstrapTable('selectPage', 1);
                return;
            }
            $('#dict-value-table').bootstrapTable('refresh');
        },
        onUncheck : function(row) {
            $('#dict-value-table').bootstrapTable('removeAll');

            $('#dict-value-toolbar button').attr('disabled', 'disabled');

            $('#btn-edit-dict-type').attr('disabled', 'disabled');
            $('#btn-del-dict-type').attr('disabled', 'disabled');
        },
        onPageChange : function(number, size) {
            $('#dict-value-table').bootstrapTable('removeAll');

            $('#dict-value-toolbar button').attr('disabled', 'disabled');

            $('#btn-edit-dict-type').attr('disabled', 'disabled');
            $('#btn-del-dict-type').attr('disabled', 'disabled');
        },
        onRefresh : function() {
            $('#dict-value-table').bootstrapTable('removeAll');

            $('#dict-value-toolbar button').attr('disabled', 'disabled');

            $('#btn-edit-dict-type').attr('disabled', 'disabled');
            $('#btn-del-dict-type').attr('disabled', 'disabled');
        }
    });

}

function initDictTable() {
    $('#dict-value-table').bootstrapTable({
        url : URLS.GET_PAGE_DICT_VALUE,
        method : 'POST',
        contentType : "application/x-www-form-urlencoded",
        cache : false,
        striped : true,
        pagination : true,
        showRefresh : true,
        showToggle : true,
        smartDisplay : true,
        singleSelect : true,
        clickToSelect : true,
        height : 343,
        minimumCountColumns : 1,
        pageSize : 5,
        pageList : [ 5, 10, 25, 50, 100, 'All' ],
        idField : 'id',
        singleSelect : true,
        toolbar : '#dict-value-toolbar',
        sidePagination : 'server',
        dataField : 'records',
        queryParams : function(e) {
            var rows = $('#dict-type-table').bootstrapTable('getSelections');
            var typeId = rows.length > 0 ? rows[0].id : null;

            var page = {
                currentPage : (e.offset / e.limit) + 1,
                pageSize : e.limit,
                typeId : typeId
            }
            return $.extend(dictParams, page);
        },
        columns : [ {
            checkbox : true,
        }, {
            field : 'value',
            title : '字典值',
        }, {
            field : 'name',
            title : '字典值名称',
        }, {
            field : 'remark',
            title : '备注',
        } ],
        onCheck : function(row, element, field) {
            if (row.canUpdate) {
                $('#btn-edit-dict-value').removeAttr('disabled');
                $('#btn-del-dict-value').removeAttr('disabled');
            } else {
                $('#btn-edit-dict-value').attr('disabled', 'disabled');
                $('#btn-del-dict-value').attr('disabled', 'disabled');
            }
        },
        onUncheck : function(row) {
            $('#btn-edit-dict-value').attr('disabled', 'disabled');
            $('#btn-del-dict-value').attr('disabled', 'disabled');
        },
        onPageChange : function(number, size) {
            $('#btn-edit-dict-value').attr('disabled', 'disabled');
            $('#btn-del-dict-value').attr('disabled', 'disabled');
        },
        onRefresh : function() {
            $('#btn-edit-dict-value').attr('disabled', 'disabled');
            $('#btn-del-dict-value').attr('disabled', 'disabled');
        }

    });
}

$(function() {

    initDictTypeTable();

    initDictTable();
})

$(function() {

    $('#dict-type-toolbar button').attr('disabled', 'disabled');
    $('#dict-type-toolbar #btn-add-dict-type').removeAttr('disabled');

    $('#dict-value-toolbar button').attr('disabled', 'disabled');

    $('#dict-type-query-btn').click(function() {
        dictTypeParams = $('#dict-type-form').serializeJson();
        $('#dict-type-table').bootstrapTable('selectPage', 1);
    });

    $('#dict-value-query-btn').click(function() {
        dictParams = $('#dict-value-form').serializeJson();
        $('#dict-value-table').bootstrapTable('selectPage', 1);
    });

    $('#dict-type-table').bootstrapTable('resetWidth');
    $('#dict-value-table').bootstrapTable('resetWidth');

    $(window).resize(function() {
        $('#dict-type-table').bootstrapTable('resetWidth');
        $('#dict-value-table').bootstrapTable('resetWidth');
    });

})