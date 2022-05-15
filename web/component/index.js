import progress  from './progressGif'
let component={
    install(vue,weex){
        weex.registerComponent('progressGif',progress)
    }
}
export  default component
