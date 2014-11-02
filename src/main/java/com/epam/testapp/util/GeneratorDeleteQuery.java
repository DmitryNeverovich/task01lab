/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.testapp.util;

/**
 * GeneratorDeleteQuery is used to generate query of delete.
 *
 * @author Dzmitry_Neviarovich
 */
public final class GeneratorDeleteQuery {

    public static String generateDeleteQuery(Integer[] ids) {

        StringBuilder sqlRequestDelete = new StringBuilder(DataUtil.SQL_DELETE_NEWS_QUERY);
        sqlRequestDelete.append(ids[0]);

        for (int j = 1; j < ids.length; j++) {
            sqlRequestDelete.append(",");
            sqlRequestDelete.append(ids[j]);
        }
        sqlRequestDelete.append(")");

        return sqlRequestDelete.toString();
    }
}
