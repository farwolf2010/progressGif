<template>
  <div v-if="isShow" class="bg" @click="onClickBackground">
    <image :src="url" :style="{'width':`${this.width}px`,'height':`${this.height}px`,'text-align':'center'}"></image>
    <text>{{msg}}</text>
  </div>
</template>

<script>
export default {
  props: {},
  data() {
    return {
      url: '',
      width: 100,
      height: 100,
      isClickClose: true,
      isShow: false,
      msg: ''
    }
  },
  methods: {
    show(param) {
      this.url = param.url
      this.width = param.width
      this.height = param.height
      if (param.isClickClose != undefined) {
        this.isClickClose = param.isClickClose
      }
      if (param.msg) {
        this.msg = param.msg
      }
      this.isShow = true
    },
    onClickBackground() {
      if (this.isClickClose) {
        this.isShow = false
      }
    },
    dismiss(){
      this.isShow = false;
    },
    onLoad(param) {

    },
    onShow() {

    },
    onHide() {

    },
    onUnload() {

    }
  },
  created() {

  }
}
</script>

<style scoped>
.bg {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, .4);
  z-index: 99999;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
</style>
