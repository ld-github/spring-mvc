var URLS = {
    INDEX_PAGE : contextPath + '/index',
    PASSWORD_PAGE : contextPath + '/password',
}

/**
 * @author LD
 */
var MENUS = [ {
    title : '操作员管理',
    submenus : [ {
        title : '用户维护',
        url : contextPath + '/login'
    }, {
        title : '角色维护',
        url : contextPath + '/login'
    }, {
        title : '权限维护',
        url : contextPath + '/login'
    } ]
}, {
    title : '系统管理',
    submenus : [ {
        title : '字典维护',
        url : contextPath + '/dict'
    } ]
}, {
    title : '日志记录',
    submenus : [ {
        title : '异常信息',
        url : contextPath + '/login'
    } ]
}, {
    title : '帮助中心',
    url : contextPath + '/help'
} ];

/**
 * Init menu to menu-panel
 */
function initMenu() {

    var menus = $('#menus');

    $.each(MENUS, function(index, item) {

        var itemed = index === 0 ? ' layui-nav-itemed' : '';

        var li = $('<LI>').addClass('layui-nav-item' + itemed);
        $('<A>').attr('href', 'javascript:;').html(item.title).addClass('menu-item').data({ 'title' : item.title, 'url' : item.url }).appendTo(li);

        if (item.submenus) {
            var dl = $('<DL>').addClass('layui-nav-child');
            $.each(item.submenus, function(index, subItem) {
                $('<dd>').append($('<A>').attr('href', 'javascript:;').html(subItem.title).addClass('menu-item').data({ 'title' : subItem.title, 'url' : subItem.url })).appendTo(dl);
            });

            dl.appendTo(li);
        }

        li.appendTo(menus);
    });

    $('.menu-item').click(function() {
        var data = $(this).data();
        if (data.url) {
            addTab(data.title, data.url);
        }
    });

}

/**
 * Add page to page-panel
 * 
 * @param title
 * @param url
 * @param closable
 */
function addTab(title, url, closable) {
    closable = typeof (closable) == 'boolean' ? closable : true;

    var tab = $('#page-tabs');

    if (tab.tabs('exists', title)) {
        tab.tabs('select', title);
        return;
    }

    tab.tabs('add', {
        title : title,
        closable : closable,
        content : $('<IFRAME>').attr({ frameborder : 0, src : url }).height($('#main-panel-body').height() - 60).addClass('page-iframe')
    });

}

$(function() {

    initMenu();

    $('#page-tabs').tabs({
        fit : true,
        tabHeight : 30,
        border : false,
        tabWidth : 120,
    });

    addTab("首页", URLS.INDEX_PAGE, false);

    layui.use([ 'element' ], function() {
        var element = layui.element();
    });

    $(window).resize(function() {
        setTimeout(function() {
            $('.page-iframe').each(function() {
                $(this).height($('#main-panel-body').height() - 60);
            })
        }, 50)
    });

    $('#update-password-btn').click(function() {
        addTab("修改密码", URLS.PASSWORD_PAGE, true);
    });

});