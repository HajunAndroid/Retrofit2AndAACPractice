package kr.co.hajun.newsviewmodel;

import androidx.paging.DataSource;

public class MyDataFactory extends DataSource.Factory {
    @Override
    public DataSource create() {
        return new MyDataSource();
    }
}
