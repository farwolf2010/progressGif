import progressGifVue from '../component/progressGif.vue'
import Vue from 'vue'
let $vm
let progressGif = {
    log(msg) {

    },
    show(param) {
        const dom = Vue.extend(progressGifVue)
        if (!$vm) {
            $vm = new dom({
                el: document.createElement('div')
            })
            document.body.appendChild($vm.$el)
            $vm.show(param)
        }else {
          $vm.show(param)
        }
    },
    dismiss() {
        if($vm){
            $vm.dismiss()
        }
    }
}
export default progressGif
