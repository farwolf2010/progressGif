//
//  WXProgressModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2019/5/28.
//

#import "WXProgressGifModule.h"

//注册module，名字叫progressGif
WX_PlUGIN_EXPORT_MODULE(progressGif, WXProgressGifModule)

@implementation WXProgressGifModule

@synthesize weexInstance;
//异步方法
//WX_EXPORT_METHOD(@selector(log:callback:))
//同步返回方法注册
WX_EXPORT_METHOD(@selector(show:))
WX_EXPORT_METHOD(@selector(dismiss))
WX_EXPORT_METHOD_SYNC(@selector(isShowing:callback:))

-(void)show:(NSMutableDictionary*)p
{
    self.width = 82;
    self.height = 82;
    self.isClickClose = YES;


    NSMutableArray *array = p[@"list"];
    if([p objectForKey:@"url"]){

        self.url = [@"" add:p[@"url"]];
        if([self.url startWith:@"root:"])
        {
            self.url=[self.url replace:@"root:" withString:[Weex getBaseUrl:self.weexInstance]];

    //        return [NSURL URLWithString:url];
        }
    }

    if([p objectForKey:@"width"]){
        self.width = [Weex length:[p[@"width"] floatValue] instance:self.weexInstance];
    }

    if([p objectForKey:@"height"]){
        self.height = [Weex length:[p[@"height"] floatValue] instance:self.weexInstance];
    }

    if([p objectForKey:@"isClickClose"]){
        self.isClickClose = [p[@"isClickClose"] boolValue];
    }
    
    if([p objectForKey:@"msg"]){
        self.msg = [NSString stringWithString:p[@"msg"]];
    } else {
        self.msg = @"";
    }

    [self showFull:@"加载中..."];
}

-(void)showFull:(NSString*)txt
{
    _p = [[LockScreenProgress1 alloc]initWith:self.weexInstance.viewController.view withUrl:self.url withWidth:self.width withHeight:self.height msg:self.msg];
    if(self.isClickClose==YES){
        [self initGesture];
    }

    [_p show:txt];
}

-(void)isShowing:(NSDictionary*)param callback:(WXModuleCallback)callback{
    callback(@([_p isHidden]));
}

-(void)dismiss{
    [_p hide];
}
-(void)initGesture {
    _p.uv.userInteractionEnabled = YES;
    UITapGestureRecognizer * _tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onTouch:)];
//    _tap.delegate = _p;
    [_p.uv addGestureRecognizer:_tap];
}

-(void)onTouch:(UITapGestureRecognizer *)tap_gest{
    if(tap_gest.numberOfTapsRequired == 1){
//        NSLog(@"单指单击");
        [_p hide];
    }else if (tap_gest.numberOfTapsRequired == 2){
//        NSLog(@"单指双击");

    }
}
@end
