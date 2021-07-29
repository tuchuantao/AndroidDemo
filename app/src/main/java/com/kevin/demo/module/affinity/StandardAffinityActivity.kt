package com.kevin.demo.module.affinity

/**
 *1、singleInstance 会新建一个task 栈，该栈有且仅有这个一个activity任务
 *
 *      （1）         （2）                                  （1）
 * 2、主任务栈  -> singleInstance / affinity (新的任务栈)  -> 主任务栈
 *    此时back键，不会回到新的任务栈中的任务，会在主任务栈中寻找上一个任务Activity
 *
 *
 *      （2）             （2）                                     （3）
 * 3、主任务栈 -> singleInstance / affinity (新的任务栈) ->  singleInstance / affinity (新的任务栈)
 *    此时back键，会(3) ->  back(2)  -> (1)
 *
 * Create by Kevin-Tu on 2020/5/29.
 */
class StandardAffinityActivity : BaseLaunchModeActivity()  {
}