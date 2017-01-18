package com.ld.web.enumeration;

/**
 * 
 *<p>Title: Gender</p>
 *<p>Copyright: Copyright (c) 2015</p>
 *<p>Description: 性别</p>
 *
 *@author LD
 *
 *@date 2015-09-22
 */
public enum Gender {
    /**
     * MAN:男 WOMAN:女 UNKNOWN:未知
     */
    MAN(0), WOMAN(1), UNKNOWN(2);

    private int value = 0;

    public int value() {
        return this.value;
    }

    public static Gender get(int value) {
        for (Gender i : Gender.values()) {
            if (value == i.value) {
                return i;
            }
        }
        return UNKNOWN;
    }

    private Gender(int value) {
        this.value = value;
    }
}
