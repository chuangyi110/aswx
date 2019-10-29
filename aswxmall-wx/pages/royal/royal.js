// pages/royal/royal.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    return(){
      url:""
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    console.log(options);
    let url = options.url;
    console.log(url);
    url = decodeURIComponent(url);
    console.log("转义字符", url);
    this.setData({
      url: url,
    })
    console.log(options)

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
  replaceSpecialChar: function (url) {
    url = url.replace('%2B', '+');
    url = url.replace('%20', ' ');
    url = url.replace('%2F', '/');
    url = url.replace('%3F', '?');
    url = url.replace('%25', '%');
    url = url.replace('%23', '#');
    url = url.replace('%26', '&');
    url = url.replace('%3D', '=');
    console.log("转义字符", url);
    return url;
  }
})