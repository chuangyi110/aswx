<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.username" clearable class="filter-item" style="width: 200px;" placeholder="请输入用户名"/>
      <el-input v-model="listQuery.mobile" clearable class="filter-item" style="width: 200px;" placeholder="请输入手机号"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list"
              element-loading-text="正在查询中。。。" border fit highlight-current-row
              @expand-change="expandSelect"
              ref="refTable"
              @selection-change="handleSelectionChange"
              >
      <el-table-column type="expand">
        返点总额：{{rebates.amount}}&#12288;&#12288;
        返点余额：{{rebates.balance}}&#12288;&#12288;
        已提现：{{rebates.widthdraw}}
        <el-table v-loading="rebatesListLoading" :data="rebatesList" element-loading-text="正在查询中。。。" border fit highlight-current-row>
          <el-table-column align = "center" label="发展客户ID" prop="orderUserId"/>
          <el-table-column align = "center" label="消费单数" prop="orderCount"/>
          <el-table-column align = "center" label="消费金额" prop="totalOrderActualPrice"/>
          <el-table-column align = "center" label="返点金额" prop="totalRebates"/>
        </el-table>
      </el-table-column>
      <el-table-column align="center" width="100px" label="用户ID" prop="id" sortable/>

      <el-table-column align="center" label="用户名" prop="username"/>

      <el-table-column align="center" label="手机号码" prop="mobile"/>

      <el-table-column align="center" label="性别" prop="gender">
        <template slot-scope="scope">
          <el-tag >{{ genderDic[scope.row.gender] }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="生日" prop="birthday"/>

      <el-table-column align="center" label="用户等级" prop="userLevel">
        <template slot-scope="scope">
          <el-tag >{{ levelDic[scope.row.userLevel] }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="状态" prop="status">
        <template slot-scope="scope">
          <el-tag>{{ statusDic[scope.row.status] }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--用户修改-->
    <el-dialog :visible.sync="userDialogVisible" title="用户修改" width="800">


    </el-dialog>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

  </div>
</template>

<script>
import { fetchList,fetchRebatesList } from '@/api/user'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'User',
  components: { Pagination },
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        username: undefined,
        mobile: undefined,
        sort: 'add_time',
        order: 'desc'
      },
      downloadLoading: false,
      genderDic: ['未知', '男', '女'],
      levelDic: ['普通用户', 'VIP用户', '高级VIP用户'],
      statusDic: ['可用', '禁用', '注销'],
      expands:[],
      rebatesListLoading: true,
      rebatesList:null,
      rebatesTotal:0,
      rebatesListQuery:{
        page:1,
        limit:20,
        uid:undefined,
        sort:'id',
        order:'desc'
      },
      rebates:{
        amount:0,
        balance:0,
        widthdraw:0,
      },
      userDialogVisible: false,
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        this.list = response.data.data.list
        this.total = response.data.data.total
        this.listLoading = false
      }).catch(() => {
        this.list = []
        this.total = 0
        this.listLoading = false
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['用户名', '手机号码', '性别', '生日', '状态']
        const filterVal = ['username', 'mobile', 'gender', 'birthday', 'status']
        excel.export_json_to_excel2(tHeader, this.list, filterVal, '用户信息')
        this.downloadLoading = false
      })
    },
    expandSelect:function (row, expandedRows) {
      var that = this
      if (expandedRows.length>1) {
        that.expands = []
        if (row) {
          that.expands.push(row);
        }
        this.$refs.refTable.toggleRowExpansion(expandedRows[0]);
      } else {
        that.expands = [];
      }
      this.rebatesListQuery.uid = row.id
      fetchRebatesList(this.rebatesListQuery).then(response=>{
        let data = response.data.data;
        console.log(response)
        this.rebatesList = data.rebatesCountList
        this.rebatesTotal = data.rebatesCountList.total
        this.rebates.amount = data.amount
        this.rebates.balance = data.balance
        this.rebates.widthdraw = data.widthdraw
        this.rebatesListLoading = false
      }).catch(() => {
        this.rebatesList = []
        this.rebatesTotal = 0
        this.rebates.amount = 0
        this.rebates.balance = 0
        this.rebates.widthdraw = 0
        this.rebatesListLoading = false
      })
    },
    handleSelectionChange:function (val) {
      console.log(val)
    },
    handleUpdate(user){
      //console.log(user)
      this.userDialogVisible = true;
    }
  }
}
</script>
