var util = require('../../utils/util.js');
var api = require('../../config/api.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getServiceDetail();

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  getServiceDetail: function () {
    wx.showLoading({
      title: '加载中',
    });

    setTimeout(function () {
      wx.hideLoading()
    }, 2000);

    let that = this;
    that.setData({
      showPage: false,
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
  previewImage: function(e) {
    // var current = e.target.dataset.src;
    // console.log(current)
    // wx.previewImage({
    //   current: current,
    //   urls: [current]
    // })
    util.previewImage(e)
  }
})