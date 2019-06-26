package com.wdd.studentmanager.util;

import java.util.List;

public class PageBean<T> {
    private Integer pageno;  //第几页
    private Integer pagesize; //每页条数
    private List<T> datas;
    private Integer totalno; //总共几页
    private Integer totalsize; //总的条数

    public PageBean(Integer pageno, Integer pagesize) {
        if(pageno<=0){
            this.pageno = 1;
        }else{
            this.pageno = pageno;
        }
        if(pagesize<=0){
            this.pagesize = 10;
        }else{
            this.pagesize = pagesize;
        }
        this.pageno = pageno;
        this.pagesize = pagesize;
    }

    public Integer getPageno() {
        return pageno;
    }

    public void setPageno(Integer pageno) {
        this.pageno = pageno;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public List getDatas() {
        return datas;
    }

    public void setDatas(List datas) {
        this.datas = datas;
    }

    public Integer getTotalno() {
        return totalno;
    }

    public void setTotalno(Integer totalno) {
        this.totalno = totalno;
    }

    public Integer getTotalsize() {
        return totalsize;
    }

    public void setTotalsize(Integer totalsize) {
        this.totalno = (totalsize%pagesize==0)?(totalsize/pagesize):(totalsize/pagesize+1);
        this.totalsize = totalsize;
    }

    /**
     * 开始索引
     */
    public Integer getStartIndex(){
        return (this.pageno-1)*this.pagesize;
    }


    @Override
    public String toString() {
        return "PageBean{" +
                "pageno=" + pageno +
                ", pagesize=" + pagesize +
                ", datas=" + datas +
                ", totalno=" + totalno +
                ", totalsize=" + totalsize +
                '}';
    }
}
