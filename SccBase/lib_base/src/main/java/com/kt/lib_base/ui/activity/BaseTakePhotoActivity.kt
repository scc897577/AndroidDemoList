package com.kt.lib_base.ui.activity

/*
    存在选择图片的Activity基础封装
 */
//abstract class BaseTakePhotoActivity<T : BasePresenter<*>> : BaseActivity(), BaseView, TakePhoto.TakeResultListener {
//
//    private lateinit var mTakePhoto:TakePhoto
//
//    private lateinit var mTempFile: File
//
//    @Inject
//    lateinit var mPresenter: T
//
//    lateinit var mActivityComponent: ActivityComponent
//
//    private lateinit var mLoadingDialog: ProgressLoading
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        initActivityInjection()
//        injectComponent()
//
//        mTakePhoto = TakePhotoImpl(this,this)
//        mTakePhoto.onCreate(savedInstanceState)
//
//        mLoadingDialog = ProgressLoading.create(this)
//        ARouter.getInstance().inject(this)
//    }
//
//    /*
//        Dagger注册
//     */
//    protected abstract fun injectComponent()
//
//    /*
//        初始化Activity Component
//     */
//    private fun initActivityInjection() {
//        mActivityComponent = DaggerActivityComponent.builder().appComponent((application as BaseApplication).appComponent)
//                .activityModule(ActivityModule(this))
//                .lifecycleProviderModule(LifecycleProviderModule(this))
//                .build()
//
//    }
//
//    /*
//        显示加载框，默认实现
//     */
//    override fun showLoading() {
//        mLoadingDialog.showLoading()
//    }
//
//    /*
//        隐藏加载框，默认实现
//     */
//    override fun hideLoading() {
//        mLoadingDialog.hideLoading()
//    }
//
//    /*
//        错误信息提示，默认实现
//     */
//    override fun onError(text:String) {
//        toast(text)
//    }
//
//    /*
//        弹出选择框，默认实现
//        可根据实际情况，自行修改
//     */
//    protected fun showAlertView() {
//        AlertView("选择图片", "", "取消", null, arrayOf("拍照", "相册"), this,
//                AlertView.Style.ActionSheet, OnItemClickListener { o, position ->
//            mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(),false)
//            when (position) {
//                0 -> {
//                    createTempFile()
//                    mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
//                }
//                1 -> mTakePhoto.onPickFromGallery()
//            }
//        }
//
//        ).show()
//
//    }
//
//    /*
//        获取图片，成功回调
//     */
//    override fun takeSuccess(result: TResult?) {
//        Log.d("TakePhoto",result?.image?.originalPath)
//        Log.d("TakePhoto",result?.image?.compressPath)
//    }
//
//    /*
//        获取图片，取消回调
//     */
//    override fun takeCancel() {
//    }
//
//    /*
//        获取图片，失败回调
//     */
//    override fun takeFail(result: TResult?, msg: String?) {
//        Log.e("takePhoto",msg)
//    }
//
//    /*
//        TakePhoto默认实现
//     */
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        mTakePhoto.onActivityResult(requestCode,resultCode,data)
//    }
//
//    /*
//        新建临时文件
//     */
//    fun createTempFile(){
//        val tempFileName = "${DateUtils.curTime}.png"
//        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
//            this.mTempFile = File(Environment.getExternalStorageDirectory(),tempFileName)
//            return
//        }
//
//        this.mTempFile = File(filesDir,tempFileName)
//    }
//}
