<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','job_title') || $check_field('add','job_title') || $check_field('set','job_title')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="职位名称" prop="job_title">
					<el-input id="job_title" v-model="form['job_title']" placeholder="请输入职位名称"
							  v-if="user_group === '管理员' || (form['position_information_id'] && $check_field('set','job_title')) || (!form['position_information_id'] && $check_field('add','job_title'))" :disabled="disabledObj['job_title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','job_title')">{{form['job_title']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','job_category') || $check_field('add','job_category') || $check_field('set','job_category')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="职位类别" prop="job_category">
					<el-select id="job_category" v-model="form['job_category']"
						v-if="user_group === '管理员' || (form['position_information_id'] && $check_field('set','job_category')) || (!form['position_information_id'] && $check_field('add','job_category'))">
						<el-option v-for="o in list_job_category" :key="o['job_category']" :label="o['job_category']"
							:value="o['job_category']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','job_category')">{{form['job_category']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','cover') || $check_field('add','cover') || $check_field('set','cover')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="封面" prop="cover">
					<el-upload :disabled="disabledObj['cover_isDisabled']" id="cover" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_cover"
						:show-file-list="false" v-if="user_group === '管理员' || (form['position_information_id'] && $check_field('set','cover')) || (!form['position_information_id'] && $check_field('add','cover'))">
						<img v-if="form['cover']" :src="$fullUrl(form['cover'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','cover')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['cover'])" :preview-src-list="[$fullUrl(form['cover'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','number_of_recruits') || $check_field('add','number_of_recruits') || $check_field('set','number_of_recruits')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="招聘人数" prop="number_of_recruits">
					<el-input-number id="number_of_recruits" v-model.number="form['number_of_recruits']"
						v-if="user_group === '管理员' || (form['position_information_id'] && $check_field('set','number_of_recruits')) || (!form['position_information_id'] && $check_field('add','number_of_recruits'))"></el-input-number>
					<div v-else-if="$check_field('get','number_of_recruits')">{{form['number_of_recruits']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','recruitment_company') || $check_field('add','recruitment_company') || $check_field('set','recruitment_company')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="招聘公司" prop="recruitment_company">
					<el-input id="recruitment_company" v-model="form['recruitment_company']" placeholder="请输入招聘公司"
							  v-if="user_group === '管理员' || (form['position_information_id'] && $check_field('set','recruitment_company')) || (!form['position_information_id'] && $check_field('add','recruitment_company'))" :disabled="disabledObj['recruitment_company_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','recruitment_company')">{{form['recruitment_company']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','resume_delivery_address') || $check_field('add','resume_delivery_address') || $check_field('set','resume_delivery_address')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="简历投递地址" prop="resume_delivery_address">
					<el-input id="resume_delivery_address" v-model="form['resume_delivery_address']" placeholder="请输入简历投递地址"
							  v-if="user_group === '管理员' || (form['position_information_id'] && $check_field('set','resume_delivery_address')) || (!form['position_information_id'] && $check_field('add','resume_delivery_address'))" :disabled="disabledObj['resume_delivery_address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','resume_delivery_address')">{{form['resume_delivery_address']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','job_requirements') || $check_field('add','job_requirements') || $check_field('set','job_requirements')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="职位要求" prop="job_requirements">
					<el-input type="textarea" id="job_requirements" v-model="form['job_requirements']" placeholder="请输入职位要求"
						v-if="user_group === '管理员' || (form['position_information_id'] && $check_field('set','job_requirements')) || (!form['position_information_id'] && $check_field('add','job_requirements'))" :disabled="disabledObj['job_requirements_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','job_requirements')">{{form['job_requirements']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','salary') || $check_field('add','salary') || $check_field('set','salary')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="薪资待遇" prop="salary">
					<el-input type="textarea" id="salary" v-model="form['salary']" placeholder="请输入薪资待遇"
						v-if="user_group === '管理员' || (form['position_information_id'] && $check_field('set','salary')) || (!form['position_information_id'] && $check_field('add','salary'))" :disabled="disabledObj['salary_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','salary')">{{form['salary']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','details') || $check_field('add','details') || $check_field('set','details')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="详情" prop="details">
					<quill-editor v-model.number="form['details']"
						v-if="user_group === '管理员' || (form['position_information_id'] && $check_field('set','details')) || (!form['position_information_id'] && $check_field('add','details')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','details')" v-html="form['details']"></div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "position_information_id",
				url_add: "~/api/position_information/add?",
				url_set: "~/api/position_information/set?",
				url_get_obj: "~/api/position_information/get_obj?",
				url_upload: "~/api/position_information/upload?",

				query: {
					"position_information_id": 0,
				},

				form: {
					"job_title":'', // 职位名称
					"job_category":'', // 职位类别
					"cover":'', // 封面
					"number_of_recruits":0, // 招聘人数
					"recruitment_company":'', // 招聘公司
					"resume_delivery_address":'', // 简历投递地址
					"job_requirements":'', // 职位要求
					"salary":'', // 薪资待遇
					"details":'', // 详情
					"position_information_id": 0, // ID

				},
				disabledObj:{
					"job_title_isDisabled": false,
					"job_category_isDisabled": false,
					"cover_isDisabled": false,
					"recruitment_company_isDisabled": false,
					"resume_delivery_address_isDisabled": false,
					"job_requirements_isDisabled": false,
					"salary_isDisabled": false,
					"details_isDisabled": false,
				},
				//职位类别选项列表
				list_job_category: [],
			}
		},
		methods: {
			/**
			 * 获取职位类别列表
			 */
			async get_list_job_category() {
				var json = await this.$get("~/api/job_classification/get_list?");
				if(json.result && json.result.list){
					this.list_job_category = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			/**
			 * 上传封面
			 * @param {Object} param图片参数
			 */
			upload_cover(param){
				this.uploadFile(param.file, "cover");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/position_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/position_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/position_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/position_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/position_information/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_job_category();
		},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
