package com.entity.view;

import com.entity.TushuChuruInoutListEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 出入库详情
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("tushu_churu_inout_list")
public class TushuChuruInoutListView extends TushuChuruInoutListEntity implements Serializable {
    private static final long serialVersionUID = 1L;




		//级联表 tushu
			/**
			* 图书编号
			*/
			private String tushuBianhao;
			/**
			* 图书名称
			*/
			private String tushuName;
			/**
			* 作者
			*/
			private String tushuZuozhe;
			/**
			* 图书封面
			*/
			private String tushuPhoto;
			/**
			* 图书类型
			*/
			private Integer tushuTypes;
				/**
				* 图书类型的值
				*/
				private String tushuValue;
			/**
			* 出版社
			*/
			private Integer chubansheTypes;
				/**
				* 出版社的值
				*/
				private String chubansheValue;
			/**
			* 单价
			*/
			private Double tushuNewMoney;
			/**
			* 出版日期
			*/
			@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
			@DateTimeFormat
			private Date tushuTime;
			/**
			* 图书库存
			*/
			private Integer tushuKucunNumber;
			/**
			* 可借阅天数
			*/
			private Integer tushuJieyue;
			/**
			* 详情信息
			*/
			private String tushuContent;

		//级联表 tushu_churu_inout
			/**
			* 出入库流水号
			*/
			private String tushuChuruInoutUuidNumber;
			/**
			* 出入库名称
			*/
			private String tushuChuruInoutName;
			/**
			* 出入库类型
			*/
			private Integer tushuChuruInoutTypes;
				/**
				* 出入库类型的值
				*/
				private String tushuChuruInoutValue;
			/**
			* 备注
			*/
			private String tushuChuruInoutContent;

	public TushuChuruInoutListView() {

	}

	public TushuChuruInoutListView(TushuChuruInoutListEntity tushuChuruInoutListEntity) {
		try {
			BeanUtils.copyProperties(this, tushuChuruInoutListEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

















				//级联表的get和set tushu
					/**
					* 获取： 图书编号
					*/
					public String getTushuBianhao() {
						return tushuBianhao;
					}
					/**
					* 设置： 图书编号
					*/
					public void setTushuBianhao(String tushuBianhao) {
						this.tushuBianhao = tushuBianhao;
					}
					/**
					* 获取： 图书名称
					*/
					public String getTushuName() {
						return tushuName;
					}
					/**
					* 设置： 图书名称
					*/
					public void setTushuName(String tushuName) {
						this.tushuName = tushuName;
					}
					/**
					* 获取： 作者
					*/
					public String getTushuZuozhe() {
						return tushuZuozhe;
					}
					/**
					* 设置： 作者
					*/
					public void setTushuZuozhe(String tushuZuozhe) {
						this.tushuZuozhe = tushuZuozhe;
					}
					/**
					* 获取： 图书封面
					*/
					public String getTushuPhoto() {
						return tushuPhoto;
					}
					/**
					* 设置： 图书封面
					*/
					public void setTushuPhoto(String tushuPhoto) {
						this.tushuPhoto = tushuPhoto;
					}
					/**
					* 获取： 图书类型
					*/
					public Integer getTushuTypes() {
						return tushuTypes;
					}
					/**
					* 设置： 图书类型
					*/
					public void setTushuTypes(Integer tushuTypes) {
						this.tushuTypes = tushuTypes;
					}


						/**
						* 获取： 图书类型的值
						*/
						public String getTushuValue() {
							return tushuValue;
						}
						/**
						* 设置： 图书类型的值
						*/
						public void setTushuValue(String tushuValue) {
							this.tushuValue = tushuValue;
						}
					/**
					* 获取： 出版社
					*/
					public Integer getChubansheTypes() {
						return chubansheTypes;
					}
					/**
					* 设置： 出版社
					*/
					public void setChubansheTypes(Integer chubansheTypes) {
						this.chubansheTypes = chubansheTypes;
					}


						/**
						* 获取： 出版社的值
						*/
						public String getChubansheValue() {
							return chubansheValue;
						}
						/**
						* 设置： 出版社的值
						*/
						public void setChubansheValue(String chubansheValue) {
							this.chubansheValue = chubansheValue;
						}
					/**
					* 获取： 单价
					*/
					public Double getTushuNewMoney() {
						return tushuNewMoney;
					}
					/**
					* 设置： 单价
					*/
					public void setTushuNewMoney(Double tushuNewMoney) {
						this.tushuNewMoney = tushuNewMoney;
					}
					/**
					* 获取： 出版日期
					*/
					public Date getTushuTime() {
						return tushuTime;
					}
					/**
					* 设置： 出版日期
					*/
					public void setTushuTime(Date tushuTime) {
						this.tushuTime = tushuTime;
					}
					/**
					* 获取： 图书库存
					*/
					public Integer getTushuKucunNumber() {
						return tushuKucunNumber;
					}
					/**
					* 设置： 图书库存
					*/
					public void setTushuKucunNumber(Integer tushuKucunNumber) {
						this.tushuKucunNumber = tushuKucunNumber;
					}
					/**
					* 获取： 可借阅天数
					*/
					public Integer getTushuJieyue() {
						return tushuJieyue;
					}
					/**
					* 设置： 可借阅天数
					*/
					public void setTushuJieyue(Integer tushuJieyue) {
						this.tushuJieyue = tushuJieyue;
					}
					/**
					* 获取： 详情信息
					*/
					public String getTushuContent() {
						return tushuContent;
					}
					/**
					* 设置： 详情信息
					*/
					public void setTushuContent(String tushuContent) {
						this.tushuContent = tushuContent;
					}


				//级联表的get和set tushu_churu_inout
					/**
					* 获取： 出入库流水号
					*/
					public String getTushuChuruInoutUuidNumber() {
						return tushuChuruInoutUuidNumber;
					}
					/**
					* 设置： 出入库流水号
					*/
					public void setTushuChuruInoutUuidNumber(String tushuChuruInoutUuidNumber) {
						this.tushuChuruInoutUuidNumber = tushuChuruInoutUuidNumber;
					}
					/**
					* 获取： 出入库名称
					*/
					public String getTushuChuruInoutName() {
						return tushuChuruInoutName;
					}
					/**
					* 设置： 出入库名称
					*/
					public void setTushuChuruInoutName(String tushuChuruInoutName) {
						this.tushuChuruInoutName = tushuChuruInoutName;
					}
					/**
					* 获取： 出入库类型
					*/
					public Integer getTushuChuruInoutTypes() {
						return tushuChuruInoutTypes;
					}
					/**
					* 设置： 出入库类型
					*/
					public void setTushuChuruInoutTypes(Integer tushuChuruInoutTypes) {
						this.tushuChuruInoutTypes = tushuChuruInoutTypes;
					}


						/**
						* 获取： 出入库类型的值
						*/
						public String getTushuChuruInoutValue() {
							return tushuChuruInoutValue;
						}
						/**
						* 设置： 出入库类型的值
						*/
						public void setTushuChuruInoutValue(String tushuChuruInoutValue) {
							this.tushuChuruInoutValue = tushuChuruInoutValue;
						}
					/**
					* 获取： 备注
					*/
					public String getTushuChuruInoutContent() {
						return tushuChuruInoutContent;
					}
					/**
					* 设置： 备注
					*/
					public void setTushuChuruInoutContent(String tushuChuruInoutContent) {
						this.tushuChuruInoutContent = tushuChuruInoutContent;
					}










}
