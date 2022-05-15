//
//  LockScreenProgress.m
//  oa
//
//  Created by 郑江荣 on 16/4/21.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "LockScreenProgress1.h"
#import  <ImageIO/ImageIO.h>


#import "UIImage+GIF.h"
#import <SDWebImage/UIView+WebCache.h>
//#import <SDWebImage/FLAnimatedImageView+WebCache.h>
#import "UIImageView+WebCache.h"
#import "Weex.h"
#import "URL.h"
#import "YYImage.h"

//#import <YYImage/YYImage.h>
//#import <YYImage/YYAnimatedImageView.h>


#define kScreenWidth [UIScreen mainScreen].bounds.size.width
#define kScreenHeight [UIScreen mainScreen].bounds.size.height

@implementation LockScreenProgress1

- (id)initWith:(UIView *)v withUrl:(NSString *)url withWidth:(CGFloat)width withHeight:(CGFloat)height msg:(NSString *)msg {
    
    self=[super init];
    self.view = v;
    self.width = width;
    self.height = height;
    self.msg = msg;
    
    
    NSURL * _url = [NSURL URLWithString:url];
    
//    if (_url.isFileURL) {
//        [self loadLocaleGifImage:_url];
//    } else {

        [self loadGifImage:_url];
//    }

    return self;
}

//download network gif image
- (void)loadGifImage:(NSURL *)url {
    
    
    NSArray *subviews = [self.view subviews];
    if([subviews count]!=0){
        for (UIView *view in subviews){
            if(view.tag == "loading"){
                [view removeFromSuperview];
            }
        }
    }
    
    
    YYImage *yyimage = [YYImage imageWithData:[NSData dataWithContentsOfURL:url]];
    YYAnimatedImageView *yyImageView = [[YYAnimatedImageView alloc] initWithImage:yyimage];
    yyImageView.frame = CGRectMake(0, 0, self.width, self.height);
    yyImageView.center = CGPointMake(kScreenWidth  / 2, kScreenHeight / 2);
    
    UIView *uv = [[UIView alloc] initWithFrame:CGRectMake(0, 0, kScreenWidth, kScreenHeight)];
    [uv setBackgroundColor:[UIColor colorWithRed:0 green:0 blue:0 alpha:0.5]];
    [uv addSubview:yyImageView];
    self.uv = uv;
    uv.tag = "loading";
    
    if(_msg != nil && _msg.length > 0) {
        UILabel * msgLabel = [[UILabel alloc] initWithFrame:yyImageView.frame];
        [msgLabel setBackgroundColor: [UIColor clearColor]]; // set background color
//        msgLabel.center = CGPointMake(kScreenWidth  / 2, kScreenHeight / 2 + 40);
        
        msgLabel.numberOfLines = 0;  //必须定义这个属性，否则UILabel不会换行
        
        UIFont *font = [UIFont systemFontOfSize:20.0];
        CGSize size = [_msg sizeWithFont:font constrainedToSize:CGSizeMake(CGRectGetWidth(CGRectMake(0, 0, self.view.bounds.size.width * 0.75, self.view.bounds.size.height/2-40)), CGRectGetHeight(self.view.bounds))];
        //根据计算结果重新设置UILabel的尺寸
        [msgLabel setFrame:CGRectMake((kScreenWidth - size.width) / 2, kScreenHeight / 2 + 40, size.width, size.height)];
        
        [msgLabel setText:_msg];
        [msgLabel setFont:font];
        [uv addSubview:msgLabel];
    }
    
    [self.view addSubview:uv];
}

- (void)show:(NSString *)s {
//    self.p.labelText = s;
//    self.p.backgroundColor= [@"000000" toColor:0.3];

    [self.uv setHidden:false];
}

- (void)show {
    [self show:@"请稍候..."];
}

- (void)hide {
    [self.uv setHidden:true];
//    [self.p removeFromSuperview];
}

- (BOOL)isShowing {
    return [self.uv isHidden];
}

@end
