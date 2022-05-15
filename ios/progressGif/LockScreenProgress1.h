//
//  LockScreenProgress.h
//  oa
//
//  Created by 郑江荣 on 16/4/21.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import <UIKit/UIKit.h>
//#import "MBProgressHUD.h"
#import "ProgressProtocol.h"
#import "Weex.h"
#import "YYImage.h"

@interface LockScreenProgress1 : UIView<ProgressProtocol>
-(id)initWith:(UIView*)v withUrl:(NSString *) url withWidth:(CGFloat)width withHeight:(CGFloat)height msg:(NSString *)msg;
@property(strong,nonatomic) UIView* view;
@property(strong,nonatomic) NSString* url;
@property(strong,nonatomic) UIView *uv;

@property(assign,nonatomic) CGFloat width;
@property(assign,nonatomic) CGFloat height;
@property(strong,nonatomic) NSString* msg;
-(void)show:(NSString*)s;
-(BOOL)isShowing;
-(void)loadLocaleGifImage:(NSString *)url;
@end
