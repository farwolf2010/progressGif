//
//  WXProgressModule.h
//  AFNetworking
//
//  Created by 郑江荣 on 2019/5/28.
//

#import <Foundation/Foundation.h>
#import "farwolf.h"
#import "farwolf_weex.h"
#import <WeexPluginLoader/WeexPluginLoader.h>
#import "WXModuleProtocol.h"
#import "LockScreenProgress1.h"
@interface WXProgressGifModule : NSObject <WXModuleProtocol>
@property(nonatomic,strong)LockScreenProgress1 *p;
@property(strong,nonatomic) NSString* url;
@property(assign,nonatomic) CGFloat width;
@property(assign,nonatomic) CGFloat height;
@property(assign,nonatomic) Boolean *isClickClose;
@property(assign,nonatomic) NSString *msg;
-(void)onTouch:(UITapGestureRecognizer *)tap_gest;
-(void)initGesture;
@end


