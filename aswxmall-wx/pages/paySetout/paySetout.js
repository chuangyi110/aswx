var util = require('../../utils/util.js');
var api = require('../../config/api.js');

var app = getApp();
Page({
  data: {
    status: false,
    orderId: 0,
    nickname:'',
    QCsrc:'',
    serviceList: [],
    showPage: false,
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    this.setData({
      orderId: options.orderId,
      status: options.status === '0' ? true : false
    })
    this.getServiceDetail();
  },
  onReady: function () {

  },
  onShow: function () {
    // 页面显示

  },
  onHide: function () {
    // 页面隐藏

  },
  onUnload: function () {
    // 页面关闭

  },
  getServiceDetail:function(){
    wx.showLoading({
      title: '加载中',
    });

    setTimeout(function () {
      wx.hideLoading()
    }, 2000);

    let that = this;
    that.setData({
      showPage:false,
      serviceList: []

    })
    util.request(api.ServiceDetail, {}).then(function (res) {
      console.log(res);
      if (res.errno === 0) {
        console.log(res);
        that.setData({
          serviceList: res.data,
          showPage: true,
        });
      }

      wx.hideLoading();
    });

  },
  /**
  *  图片预览方法
  *  此处注意的一点就是，调用 "wx.previewImage"时，第二个参数要求为数组形式哦
  *  当然，做过图片上传功能的应该会注意到，如果涉及到多张图片预览，图片链接数组集合即为参数 urls！
  */
  previewImage: function (e) {
    var current = e.target.dataset.src;
    console.log(current)
    wx.previewImage({
      current: current,
      urls: [current]
    })
  },
  payOrder() {
    let that = this;
    // 模拟支付成功，同理，后台也仅仅是返回一个成功的消息而已
    // wx.showModal({
    //   title: '目前不能微信支付',
    //   content: '点击确定模拟支付成功，点击取消模拟未支付成功',
    //   success: function (res) {
    //     if (res.confirm) {
    //       util.request(api.OrderPrepay, { orderId: that.data.orderId }, 'POST').then(res => {
    //         if (res.errno === 0) {
    //           that.setData({
    //             status: true
    //           });
    //         }
    //         else {
    //           util.showErrorToast('支付失败');
    //         }
    //       });
    //     }
    //     else if (res.cancel) {
    //       util.showErrorToast('支付失败');
    //     }

    //   }
    // });

    util.request(api.OrderPrepay, {
      orderId: that.data.orderId
    }, 'POST').then(function (res) {
      if (res.errno === 0) {
        const payParam = res.data;
        console.log("支付过程开始")
        wx.requestPayment({
          'timeStamp': payParam.timeStamp,
          'nonceStr': payParam.nonceStr,
          'package': payParam.packageValue,
          'signType': payParam.signType,
          'paySign': payParam.paySign,
          'success': function (res) {
            console.log("支付过程成功")
            that.setData({
              status: true
            });
          },
          'fail': function (res) {
            console.log("支付过程失败")
            util.showErrorToast('支付失败');
          },
          'complete': function (res) {
            console.log("支付过程结束")
          }
        });
      }
    });
  }
})