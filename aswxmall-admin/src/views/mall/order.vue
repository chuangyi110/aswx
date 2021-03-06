<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.userId" clearable class="filter-item" style="width: 200px;" placeholder="请输入用户ID"/>
      <el-input v-model="listQuery.orderSn" clearable class="filter-item" style="width: 200px;" placeholder="请输入订单编号"/>
      <el-select v-model="listQuery.orderStatusArray" multiple style="width: 200px" class="filter-item" placeholder="请选择订单状态">
        <el-option v-for="(key, value) in statusMap" :key="key" :label="key" :value="value"/>
      </el-select>
      <el-button v-permission="['GET /admin/order/list']" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>

      <el-table-column align="center" min-width="100" label="订单编号" prop="orderSn"/>

      <el-table-column align="center" label="用户ID" prop="userId"/>

      <el-table-column align="center" label="订单状态" prop="orderStatus">
        <template slot-scope="scope">
          <el-tag>{{ scope.row.orderStatus | orderStatusFilter }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="订单金额" prop="orderPrice"/>

      <el-table-column align="center" label="支付金额" prop="actualPrice"/>

      <el-table-column align="center" label="支付时间" prop="payTime"/>

      <el-table-column align="center" label="物流单号" prop="shipSn"/>

      <el-table-column align="center" label="物流渠道" prop="shipChannel"/>

      <el-table-column align="center" label="操作" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-permission="['GET /admin/order/detail']" type="primary" size="mini" @click="handleDetail(scope.row)">详情</el-button>
          <el-button v-permission="['POST /admin/order/revise']" v-if="scope.row.orderStatus===101" type="primary" size="mini" @click="handleRevisedPrice(scope.row)">改价</el-button>
          <el-button v-permission="['POST /admin/order/ship']" v-if="scope.row.orderStatus==201" type="primary" size="mini" @click="handleShip(scope.row)">发货</el-button>
          <el-button v-permission="['POST /admin/order/refund']" v-if="scope.row.orderStatus==202" type="primary" size="mini" @click="handleRefund(scope.row)">退款</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!-- 订单详情对话框 -->
    <el-dialog :visible.sync="orderDialogVisible" title="订单详情" width="800">

      <el-form :data="orderDetail" label-position="left">
        <el-form-item label="订单编号">
          <span>{{ orderDetail.order.orderSn }}</span>
        </el-form-item>
        <el-form-item label="订单状态">
          <el-tag>{{ orderDetail.order.orderStatus | orderStatusFilter }}</el-tag>
        </el-form-item>
        <el-form-item label="订单用户">
          <span>{{ orderDetail.user.nickname }}</span>
        </el-form-item>
        <el-form-item label="用户留言">
          <span>{{ orderDetail.order.message }}</span>
        </el-form-item>
        <el-form-item label="收货信息">
          <span>（收货人）{{ orderDetail.order.consignee }}</span>
          <span>（手机号）{{ orderDetail.order.mobile }}</span>
          <span>（地址）{{ orderDetail.order.address }}</span>
        </el-form-item>
        <el-form-item label="商品信息">
          <el-table :data="orderDetail.orderGoods" border fit highlight-current-row>
            <el-table-column align="center" label="商品名称" prop="goodsName" />
            <el-table-column align="center" label="商品编号" prop="goodsSn" />
            <el-table-column align="center" label="货品规格" prop="specifications" />
            <el-table-column align="center" label="货品价格" prop="price" />
            <el-table-column align="center" label="货品数量" prop="number" />
            <el-table-column align="center" label="货品图片" prop="picUrl">
              <template slot-scope="scope">
                <img :src="scope.row.picUrl" width="40">
              </template>
            </el-table-column>
          </el-table>
        </el-form-item>
        <el-form-item label="费用信息">
          <span>
            (实际费用){{ orderDetail.order.actualPrice }}元 =
            (商品总价){{ orderDetail.order.goodsPrice }}元 +
            (快递费用){{ orderDetail.order.freightPrice }}元 -
            (优惠减免){{ orderDetail.order.couponPrice }}元 -
            (积分减免){{ orderDetail.order.integralPrice }}元 -
            (管理员减免){{orderDetail.order.revisePrice}}元 -
            (管理员快递减免){{orderDetail.order.reviseFreightPrice}}元
          </span>

        </el-form-item>
        <el-form-item label="支付信息"  >
          <span v-if="orderDetail.order.payType==102">（支付渠道）微信支付</span>
          <span v-if="orderDetail.order.payType==101">（支付渠道）微信转账支付</span>
          <span>（支付时间）{{ orderDetail.order.payTime }}</span>
        </el-form-item>
        <el-form-item v-if="orderDetail.order.payType==101">
          <img :class="{'active':isChoose}" :src="orderDetail.order.transferPic" width="80" @click="imgScc"/>
        </el-form-item>
        <el-form-item label="快递信息">
          <span>（快递公司）{{ orderDetail.order.shipChannel }}</span>
          <span>（快递单号）{{ orderDetail.order.shipSn }}</span>
          <span>（发货时间）{{ orderDetail.order.shipTime }}</span>
        </el-form-item>
        <el-form-item label="收货信息">
          <span>（确认收货时间）{{ orderDetail.order.confirmTime }}</span>
        </el-form-item>
      </el-form>
      <el-form ref="confirmPaymentForm" :model="confirmPaymentForm"  v-if="orderDetail.order.orderStatus===101">
        <el-form-item label="转账单号" prop="revisePrice">
          <el-input v-model="confirmPaymentForm.transferAccountsOrderId" clearable />
        </el-form-item>
        <el-form-item label="规格图片" prop="picUrl" >
          <el-upload
            :headers="headers"
            :action="uploadPath"
            :show-file-list="false"
            :on-success="uploadtransferAccountsOrderPicUrl"
            :before-upload="checkFileSize"
            class="avatar-uploader"
            accept=".jpg,.jpeg,.png,.gif">
            <img v-if="confirmPaymentForm.picUrl" :src="confirmPaymentForm.picUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"/>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" v-if="orderDetail.order.orderStatus===101">
        <el-button @click="orderDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="confirmPayment( orderDetail.order.orderSn )">确认收款</el-button>
      </div>
    </el-dialog>
    <!--价格修改对话框-->
    <el-dialog :visible.sync="revisePriceDialogVisible" title="改价">
      <el-form ref="revisePriceForm" :model="revisePriceForm" :inline="true" status-icon label-position="left" label-width="200px" style="width: 400px; margin-left:50px;">
        <el-form-item label="订单价(含邮费)">
          {{revisePriceForm.orderPrice}}
        </el-form-item>
        <br/>
        <el-form-item label="已减免" v-if="!revised">
          {{revisePriceForm.revisePrice}}
        </el-form-item>
        <el-form-item label="实际支付价(含邮费)" v-if="!revised">
          {{revisePriceForm.actualPrice}}
        </el-form-item>
        <el-form-item label="减价(含邮费)" prop="revisePrice" v-if="revised">
          <el-input-number v-model="revisePriceForm.revisePrice" :min = "0" :max = "revisePriceForm.orderPrice/10" clearable />
        </el-form-item>
        <el-form-item label="现价(含邮费)" v-if="revised">
          {{revisePriceForm.orderPrice-revisePriceForm.revisePrice}}
        </el-form-item>
        <br/>
        <el-form-item label="原邮费" >
          {{revisePriceForm.freightPrice}}
        </el-form-item>
        <br/>
        <el-form-item label="已减免邮费" v-if="!revised">
          {{revisePriceForm.reviseFreightPrice}}
        </el-form-item>
        <br/>
        <el-form-item label="减免邮费" prop="reviseFreightPrice" v-if="revised" >
          <el-input-number v-model="revisePriceForm.reviseFreightPrice" :min = "0" :max = "revisePriceForm.freightPrice" clearable/>
        </el-form-item>
        <el-form-item label="实际支付邮费">
          {{revisePriceForm.freightPrice-revisePriceForm.reviseFreightPrice}}
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" >
        <el-button @click="revisePriceDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="confirmRevisedPrice">确定</el-button>
      </div>
    </el-dialog>
    <!-- 发货对话框 -->
    <el-dialog :visible.sync="shipDialogVisible" title="发货">
      <el-form ref="shipForm" :model="shipForm" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item label="快递公司" prop="shipChannel">
          <el-input v-model="shipForm.shipChannel"/>
        </el-form-item>
        <el-form-item label="快递编号" prop="shipSn">
          <el-input v-model="shipForm.shipSn"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="shipDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="confirmShip">确定</el-button>
      </div>
    </el-dialog>

    <!-- 退款对话框 -->
    <el-dialog :visible.sync="refundDialogVisible" title="退款">
      <el-form ref="refundForm" :model="refundForm" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item label="退款金额" prop="refundMoney">
          <el-input v-model="refundForm.refundMoney" :disabled="true"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="refundDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="confirmRefund">确定</el-button>
      </div>
    </el-dialog>

  </div>
</template>
<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #20a0ff;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 120px;
    height: 120px;
    line-height: 120px;
    text-align: center;
  }
  .avatar {
    width: 145px;
    height: 145px;
    display: block;
  }
  img {
    transform: scale(1);          /*图片原始大小1倍*/
    transition: all ease 0.5s; }  /*图片放大所用时间*/


  img.active {
    transform: scale(5);          /*图片需要放大3倍*/
    display: block;
    position: absolute;/*是相对于前面的容器定位的，此处要放大的图片，不能使用position：relative；以及float，否则会导致z-index无效*/
    left: 0;
    right: 0;
    margin: auto;
    top:-300px;
    z-index: 100; }
</style>
<script>
import { listOrder, shipOrder, refundOrder, detailOrder,reviseOrder,confirmPayment } from '@/api/order'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import checkPermission from '@/utils/permission' // 权限判断函数
import { getToken } from '@/utils/auth'
import { createStorage, uploadPath } from '@/api/storage'
const statusMap = {
  101: '未付款',
  102: '用户取消',
  103: '系统取消',
  201: '已付款',
  202: '申请退款',
  203: '已退款',
  301: '已发货',
  401: '用户收货',
  402: '系统收货'
}

export default {
  name: 'Order',
  components: { Pagination },
  filters: {
    orderStatusFilter(status) {
      return statusMap[status]
    }
  },
  data() {
    return {
      uploadPath,
      list: [],
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        id: undefined,
        name: undefined,
        orderStatusArray: [],
        sort: 'add_time',
        order: 'desc'
      },
      statusMap,
      orderDialogVisible: false,
      orderDetail: {
        order: {},
        user: {},
        orderGoods: []
      },
      revisePriceDialogVisible: false,
      revised:false,
      revisePriceForm:{
        orderId: undefined,
        orderPrice:undefined,
        actualPrice:undefined,
        freightPrice:undefined,
        revisePrice: undefined,
        reviseFreightPrice:undefined,
      },
      shipForm: {
        orderId: undefined,
        shipChannel: undefined,
        shipSn: undefined
      },
      shipDialogVisible: false,
      refundForm: {
        orderId: undefined,
        refundMoney: undefined
      },
      refundDialogVisible: false,
      downloadLoading: false,
      confirmPaymentForm:{
        transferAccountsOrderId:'',
        picUrl:'',
        orderSn:'',
      },
      isChoose:false,
    }
  },
  computed: {
    headers() {
      return {
        'X-Aswxmall-Admin-Token': getToken()
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    checkPermission,
    getList() {
      this.listLoading = true
      listOrder(this.listQuery).then(response => {
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
    handleDetail(row) {
      detailOrder(row.id).then(response => {
        this.orderDetail = response.data.data
      })
      this.orderDialogVisible = true
    },
    handleRevisedPrice(row){
      this.revisePriceDialogVisible = true
      this.revisePriceForm.orderId = row.id
      this.revisePriceForm.actualPrice = row.actualPrice
      this.revisePriceForm.orderPrice = row.orderPrice
      this.revisePriceForm.freightPrice = row.freightPrice
      this.revisePriceForm.revisePrice = row.revisePrice
      this.revisePriceForm.reviseFreightPrice = row.reviseFreightPrice
      if(this.revisePriceForm.revisePrice==0){
        this.revised = true
      }
    },
    confirmRevisedPrice(){
      if(!this.revised){
        this.$notify.error({
          title: '警告',
          message: '已改价一次，禁止重复修改'
        })
        return
      }
      this.$refs['revisePriceForm'].validate((valid)=>{
        if(valid){
          reviseOrder(this.revisePriceForm).then(response =>{
            this.revisePriceDialogVisible =false
            console.log(response)
            let data = response.data
            if(data.errno==0){
              this.$notify.success({
                title: '成功',
                message: '修改价格成功'
              })
              this.revised = false
              this.getList()
            }
          })
        }
      })

    },
    confirmPayment(sn){
      this.confirmPaymentForm.orderSn = sn;
      this.$refs['confirmPaymentForm'].validate((valid)=>{
        if(valid){
          confirmPayment(this.confirmPaymentForm).then(response => {
            this.$notify.success({
              title: '成功',
              message: '确认支付成功'
            })
            this.getList()
            this.orderDialogVisible= false
          }).catch(() => {
            this.$notify.error({
              title: '失败',
              message: response.data.errmsg
            })
          })
        }
      })
    },
    handleShip(row) {
      this.shipForm.orderId = row.id
      this.shipForm.shipChannel = row.shipChannel
      this.shipForm.shipSn = row.shipSn

      this.shipDialogVisible = true
      this.$nextTick(() => {
        this.$refs['shipForm'].clearValidate()
      })
    },
    confirmShip() {
      this.$refs['shipForm'].validate((valid) => {
        if (valid) {
          shipOrder(this.shipForm).then(response => {
            this.shipDialogVisible = false
            this.$notify.success({
              title: '成功',
              message: '确认发货成功'
            })
            this.getList()
          }).catch(response => {
            this.$notify.error({
              title: '失败',
              message: response.data.errmsg
            })
          })
        }
      })
    },
    handleRefund(row) {
      this.refundForm.orderId = row.id
      this.refundForm.refundMoney = row.actualPrice

      this.refundDialogVisible = true
      this.$nextTick(() => {
        this.$refs['refundForm'].clearValidate()
      })
    },
    confirmRefund() {
      this.$refs['refundForm'].validate((valid) => {
        if (valid) {
          refundOrder(this.refundForm).then(response => {
            this.refundDialogVisible = false
            this.$notify.success({
              title: '成功',
              message: '确认退款成功'
            })
            this.getList()
          }).catch(response => {
            this.$notify.error({
              title: '失败',
              message: response.data.errmsg
            })
          })
        }
      })
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['订单ID', '订单编号', '用户ID', '订单状态', '是否删除', '收货人', '收货联系电话', '收货地址']
        const filterVal = ['id', 'orderSn', 'userId', 'orderStatus', 'isDelete', 'consignee', 'mobile', 'address']
        excel.export_json_to_excel2(tHeader, this.list, filterVal, '订单信息')
        this.downloadLoading = false
      })
    },
    checkFileSize: function(file) {
      if (file.size > 1048576) {
        this.$message.error(`${file.name}文件大于1024KB，请选择小于1024KB大小的图片`)
        return false
      }
      return true
    },
    uploadtransferAccountsOrderPicUrl: function(response) {
      this.confirmPaymentForm.picUrl = response.data.url
    },
    imgScc:function () {
      this.isChoose = !this.isChoose

    }
  }
}
</script>
