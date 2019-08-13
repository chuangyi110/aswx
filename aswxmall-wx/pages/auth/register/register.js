var api = require('../../../config/api.js');
var check = require('../../../utils/check.js');

var app = getApp();
Page({
  data: {
    username: '',
    password: '',
    confirmPassword: '',
    referrerMobile: '',
    referrerMobileCheck: false,
    referrerMobileCheckImg:'/static/images/check-circle.svg',
    mobile: '',
    code: ''
  },
  onLoad: function(options) {
    // 页面初始化 options为页面跳转所带来的参数
    // 页面渲染完成

  },
  onReady: function() {

  },
  onShow: function() {
    // 页面显示

  },
  onHide: function() {
    // 页面隐藏

  },
  onUnload: function() {
    // 页面关闭

  },
  sendCode: function() {
    let that = this;

    if (this.data.mobile.length == 0) {
      wx.showModal({
        title: '错误信息',
        content: '手机号不能为空',
        showCancel: false
      });
      return false;
    }

    if (!check.isValidPhone(this.data.mobile)) {
      wx.showModal({
        title: '错误信息',
        content: '手机号输入不正确',
        showCancel: false
      });
      return false;
    }

    wx.request({
      url: api.AuthRegisterCaptcha,
      data: {
        mobile: that.data.mobile
      },
      method: 'POST',
      header: {
        'content-type': 'application/json'
      },
      success: function(res) {
        if (res.data.errno == 0) {
          wx.showModal({
            title: '发送成功',
            content: '验证码已发送',
            showCancel: false
          });
        } else {
          wx.showModal({
            title: '错误信息',
            content: res.data.errmsg,
            showCancel: false
          });
        }
      }
    });
  },
  //sync 判断是否需要同步账号信息，0不同步 1同步
  requestRegister: function(wxCode,sync) {
    let that = this;
    wx.request({
      url: api.AuthRegister,
      data: {
        username: that.data.username,
        password: that.data.password,
        mobile: that.data.mobile,
        referrerMobile :that.data.referrerMobile,
        code: that.data.code,
        wxCode: wxCode,
        sync :sync
      },
      method: 'POST',
      header: {
        'content-type': 'application/json'
      },
      success: function(res) {
        if (res.data.errno == 0) {
          app.globalData.hasLogin = true;
          wx.setStorageSync('userInfo', res.data.data.userInfo);
          wx.setStorage({
            key: "token",
            data: res.data.data.token,
            success: function() {
              wx.switchTab({
                url: '/pages/ucenter/index/index'
              });
            }
          });
        } else if(res.data.errno == 709){
          wx.showModal({
            title: '错误信息',
            content: res.data.errmsg,
            showCancel: true,
            success: function (res) {
              if (res.cancel) {
                //点击取消,默认隐藏弹框
              } else {
                //点击确定
                wx.login({
                  success: function (res) {
                    if (!res.code) {
                      wx.showModal({
                        title: '错误信息',
                        content: '注册失败',
                        showCancel: false
                      });
                    }

                    that.requestRegister(res.code, 1);
                  }
                });
              }
            },
          });
        }else{
          wx.showModal({
            title: '错误信息',
            content: res.data.errmsg,
            showCancel: true
          })
        }
      }
    });
  },
  startRegister: function() {
    var that = this;

    if (this.data.password.length < 6 || this.data.username.length < 6) {
      wx.showModal({
        title: '错误信息',
        content: '用户名和密码不得少于6位',
        showCancel: false
      });
      return false;
    }

    if (this.data.password != this.data.confirmPassword) {
      wx.showModal({
        title: '错误信息',
        content: '确认密码不一致',
        showCancel: false
      });
      return false;
    }
    if (this.data.referrerMobile!='') {
      if (!check.isValidPhone(this.data.referrerMobile)){
        wx.showModal({
          title: '错误信息',
          content: '推荐人手机号码输入不正确',
          showCancel: false
        })
        return false;
      }
      if(!this.data.referrerMobileCheck){
        wx.showModal({
          title: '错误信息',
          content: '推荐人手机未确认,请点击对号按钮确认推荐人信息',
          showCancel:false
        })
        return false;
      }
    }

    if (this.data.mobile.length == 0 //|| this.data.code.length == 0
    ) {
      wx.showModal({
        title: '错误信息',
        content: '手机号和验证码不能为空',
        showCancel: false
      });
      return false;
    }
    
    if (!check.isValidPhone(this.data.mobile)) {
      wx.showModal({
        title: '错误信息',
        content: '手机号输入不正确',
        showCancel: false
      });
      return false;
    }

    wx.login({
      success: function(res) {
        if (!res.code) {
          wx.showModal({
            title: '错误信息',
            content: '注册失败',
            showCancel: false
          });
        }
        console.log(res)
        that.requestRegister(res.code,0);
      }
    });
  },
  bindUsernameInput: function(e) {

    this.setData({
      username: e.detail.value
    });
  },
  bindPasswordInput: function(e) {

    this.setData({
      password: e.detail.value
    });
  },
  bindConfirmPasswordInput: function(e) {

    this.setData({
      confirmPassword: e.detail.value
    });
  },
  bindReferrerMobileInput:function(e){
      this.setData({
        referrerMobile: e.detail.value,
        referrerMobileCheck: false,
        referrerMobileCheckImg: '/static/images/check-circle.svg'
      });
  },
  bindMobileInput: function(e) {

    this.setData({
      mobile: e.detail.value
    });
  },
  bindCodeInput: function(e) {

    this.setData({
      code: e.detail.value
    });
  },

  chickPhone:function(e){
    let that = this

    //确认信息后不再重复确认
    if (this.data.referrerMobileCheck) {
      return;
    }

    wx.request({
      url: api.AuthReferrerMobileCheck,
      data: {
        referrerMobile: that.data.referrerMobile,
      },
      method: 'POST',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        if (res.data.errno == 0) {
          let nickName = res.data.data.nickName
          wx.showModal({
            title: '确认信息',
            content: '持有人'+nickName,
            showCancel: true,
            success: function (res) {
              if (res.cancel) {
                //点击取消,默认隐藏弹框
              } else {
                //点击确定
                that.setData({
                  referrerMobileCheck: true,
                  referrerMobileCheckImg: '/static/images/check-circle-green.svg'
                })

              }
            },
          })
        } else {
          wx.showModal({
            title: '错误信息',
            content: res.data.errmsg,
            showCancel: false
          })
        }
      }
    })
  },
  clearInput: function(e) {
    switch (e.currentTarget.id) {
      case 'clear-username':
        this.setData({
          username: ''
        });
        break;
      case 'clear-password':
        this.setData({
          password: ''
        });
        break;
      case 'clear-confirm-password':
        this.setData({
          confirmPassword: ''
        });
        break;
      case 'clear-mobile':
        this.setData({
          mobile: ''
        });
        break;
      case 'clear-referrerMobile':
        this.setData({
          referrerMobile: ''
        });
        break;
      case 'clear-code':
        this.setData({
          code: ''
        });
        break;
    }
  }
})