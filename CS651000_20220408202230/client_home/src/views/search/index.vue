<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="求职资讯"
				source_table="article"
			  ></list_result_search>
				<!-- 论坛搜索结果 -->
			  <list_result_search
				:list="result_forum"
				title="交流论坛"
				source_table="forum"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/registered_user/list', 'get')"
				:list="result_registered_user_user_name"
				title="注册用户用户名"
				source_table="registered_user"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/job_classification/list', 'get')"
				:list="result_job_classification_job_category"
				title="职位分类职位类别"
				source_table="job_classification"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/position_information/list', 'get')"
				:list="result_position_information_job_title"
				title="职位信息职位名称"
				source_table="position_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/position_information/list', 'get')"
				:list="result_position_information_job_category"
				title="职位信息职位类别"
				source_table="position_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/position_information/list', 'get')"
				:list="result_position_information_recruitment_company"
				title="职位信息招聘公司"
				source_table="position_information"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
	  "result_forum": [],
			"result_registered_user_user_name":[],
			"result_job_classification_job_category":[],
			"result_position_information_job_title":[],
			"result_position_information_job_category":[],
			"result_position_information_recruitment_company":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取交流论坛
	 */
	get_forum() {
	  this.$get("~/api/forum/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_forum = json.result.list;
		}
	  });
	},
	/**
	 * 获取user_name
	 */
	get_registered_user_user_name(){
		this.$get("~/api/registered_user/get_list?like=0", { page: 1, size: 10, "user_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_registered_user_user_name = json.result.list;
			result_registered_user_user_name.map(o => o.title = o['user_name'])
	  			this.result_registered_user_user_name = result_registered_user_user_name
		 	}
		});
	},
	/**
	 * 获取job_category
	 */
	get_job_classification_job_category(){
		this.$get("~/api/job_classification/get_list?like=0", { page: 1, size: 10, "job_category": this.query.word }, (json) => {
		  if (json.result) {
			var result_job_classification_job_category = json.result.list;
			result_job_classification_job_category.map(o => o.title = o['job_category'])
	  			this.result_job_classification_job_category = result_job_classification_job_category
		 	}
		});
	},
	/**
	 * 获取job_title
	 */
	get_position_information_job_title(){
		this.$get("~/api/position_information/get_list?like=0", { page: 1, size: 10, "job_title": this.query.word }, (json) => {
		  if (json.result) {
			var result_position_information_job_title = json.result.list;
			result_position_information_job_title.map(o => o.title = o['job_title'])
	  			this.result_position_information_job_title = result_position_information_job_title
		 	}
		});
	},
	/**
	 * 获取job_category
	 */
	get_position_information_job_category(){
		this.$get("~/api/position_information/get_list?like=0", { page: 1, size: 10, "job_category": this.query.word }, (json) => {
		  if (json.result) {
			var result_position_information_job_category = json.result.list;
			result_position_information_job_category.map(o => o.title = o['job_category'])
	  			this.result_position_information_job_category = result_position_information_job_category
		 	}
		});
	},
	/**
	 * 获取recruitment_company
	 */
	get_position_information_recruitment_company(){
		this.$get("~/api/position_information/get_list?like=0", { page: 1, size: 10, "recruitment_company": this.query.word }, (json) => {
		  if (json.result) {
			var result_position_information_recruitment_company = json.result.list;
			result_position_information_recruitment_company.map(o => o.title = o['recruitment_company'])
	  			this.result_position_information_recruitment_company = result_position_information_recruitment_company
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
	this.get_forum();
		this.get_registered_user_user_name();
		this.get_job_classification_job_category();
		this.get_position_information_job_title();
		this.get_position_information_job_category();
		this.get_position_information_recruitment_company();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_forum();
	  this.get_registered_user_user_name();
	  this.get_job_classification_job_category();
	  this.get_position_information_job_title();
	  this.get_position_information_job_category();
	  this.get_position_information_recruitment_company();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
