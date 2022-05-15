import progressGif  from './progressGif'
import progressDom from '../component/progressGif.vue'
let $vm
let module={
    install(vue,weex){
        weex.registerModule('progressGif',progressGif)
    }
}
export  default module
