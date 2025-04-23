package com.entity.view;

import com.entity.JieyueEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 借阅记录
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("jieyue")
public class JieyueView extends JieyueEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 借阅状态的值
		*/
		private String jieyueValue;



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

		//级联表 yonghu
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 头像
			*/
			private String yonghuPhoto;
			/**
			* 联系方式
			*/
			private String yonghuPhone;
			/**
			* 用户身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 系别
			*/
			private Integer xibieTypes;
				/**
				* 系别的值
				*/
				private String xibieValue;
			/**
			* 班级
			*/
			private Integer banjiTypes;
				/**
				* 班级的值
				*/
				private String banjiValue;
			/**
			* 邮箱
			*/
			private String yonghuEmail;
			/**
			* 图书证号
			*/
			private String yonghuUuidNumber;
			/**
			* 可借阅数量
			*/
			private Integer jieyueshuliang;
			/**
			* 假删
			*/
			private Integer yonghuDelete;

	public JieyueView() {

	}

	public JieyueView(JieyueEntity jieyueEntity) {
		try {
			BeanUtils.copyProperties(this, jieyueEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 借阅状态的值
			*/
			public String getJieyueValue() {
				return jieyueValue;
			}
			/**
			* 设置： 借阅状态的值
			*/
			public void setJieyueValue(String jieyueValue) {
				this.jieyueValue = jieyueValue;
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








				//级联表的get和set yonghu
					/**
					* 获取： 用户姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 用户姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}
					/**
					* 获取： 头像
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 头像
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}
					/**
					* 获取： 联系方式
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 联系方式
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}
					/**
					* 获取： 用户身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 用户身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}
					/**
					* 获取： 系别
					*/
					public Integer getXibieTypes() {
						return xibieTypes;
					}
					/**
					* 设置： 系别
					*/
					public void setXibieTypes(Integer xibieTypes) {
						this.xibieTypes = xibieTypes;
					}


						/**
						* 获取： 系别的值
						*/
						public String getXibieValue() {
							return xibieValue;
						}
						/**
						* 设置： 系别的值
						*/
						public void setXibieValue(String xibieValue) {
							this.xibieValue = xibieValue;
						}
					/**
					* 获取： 班级
					*/
					public Integer getBanjiTypes() {
						return banjiTypes;
					}
					/**
					* 设置： 班级
					*/
					public void setBanjiTypes(Integer banjiTypes) {
						this.banjiTypes = banjiTypes;
					}


						/**
						* 获取： 班级的值
						*/
						public String getBanjiValue() {
							return banjiValue;
						}
						/**
						* 设置： 班级的值
						*/
						public void setBanjiValue(String banjiValue) {
							this.banjiValue = banjiValue;
						}
					/**
					* 获取： 邮箱
					*/
					public String getYonghuEmail() {
						return yonghuEmail;
					}
					/**
					* 设置： 邮箱
					*/
					public void setYonghuEmail(String yonghuEmail) {
						this.yonghuEmail = yonghuEmail;
					}
					/**
					* 获取： 图书证号
					*/
					public String getYonghuUuidNumber() {
						return yonghuUuidNumber;
					}
					/**
					* 设置： 图书证号
					*/
					public void setYonghuUuidNumber(String yonghuUuidNumber) {
						this.yonghuUuidNumber = yonghuUuidNumber;
					}
					/**
					* 获取： 可借阅数量
					*/
					public Integer getJieyueshuliang() {
						return jieyueshuliang;
					}
					/**
					* 设置： 可借阅数量
					*/
					public void setJieyueshuliang(Integer jieyueshuliang) {
						this.jieyueshuliang = jieyueshuliang;
					}
					/**
					* 获取： 假删
					*/
					public Integer getYonghuDelete() {
						return yonghuDelete;
					}
					/**
					* 设置： 假删
					*/
					public void setYonghuDelete(Integer yonghuDelete) {
						this.yonghuDelete = yonghuDelete;
					}




}
