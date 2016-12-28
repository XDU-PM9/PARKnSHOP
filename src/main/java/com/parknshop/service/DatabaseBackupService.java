package com.parknshop.service;

import java.io.File;
import java.util.Collection;
import java.util.List;

/**
 * Created by niewenzhi on 2016/12/24.
 */
public interface DatabaseBackupService {
    /*true 成功
    false 失败*/

     Boolean backup();
    Boolean rollback(String filename);
    Boolean deletebackup(String filname);
    /*在controller中用迭代器拿到filename
    * 以防需要其他属性 我直接返回的是file集合 而不是filename集合*/
    Collection getallfile();
}
