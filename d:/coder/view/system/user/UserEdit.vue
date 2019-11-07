<template>
    <el-form :model="data" :rules="rules" ref="editForm" label-width="120px">
        <el-row>
        </el-row>
        <el-row>
            <sFormItem2 align="right" col-span="24">
                <el-button type="primary" @click="handleSubmit('editForm')" v-show="operator!='view'">{{$t('ok')}}</el-button>
                <el-button @click="handleCancel">{{$t('cancel')}}</el-button>
            </sFormItem2>
        </el-row>
    </el-form>
</template>

<script>
    export default {
        name: "UserEdit",
        model: {
            prop: 'dialogIsVisible',
            event: 'closeDialog'
        },
        props: {
            dialogIsVisible: {
                required: true
            },
            operator: {
                type: String,
                required: true
            },
            data: {type: Object}
        },
        data() {
            return {
                rules: {
                }
            }
        },
        methods: {

            /**
			 * 点击取消按钮时关闭对话框
             */
            handleCancel() {
                this.$emit('closeDialog', false)
            },

            /**
			 * 点击确定按钮时保存数据
             * @param formName
             * @returns {boolean}
             */
            handleSubmit(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        if (this.operator=="edit") {
                            this.userEdit()
                        } else {
                            this.userAdd()
                        }
                    } else {
                        console.log('error submit!!');
						return false;
					}
            });

            },

            /**
			 * 添加服务调用
             */
            userAdd() {
                this.$service.post('/system/users', this.data)
                        .then(r => {
                    if (r.data.code === this.$successCode) {
                        this.$emit('closeDialog', false)
                        this.$emit('loadData')
                        this.$Message({
                            showClose: true,
                            message: this.$t('text.success'),
                            type: 'success'
                        })
                    }else{
                        this.$Message({
                            showClose: true,
                            message: this.$t('text.failure')+r.data.msg,
                            type: 'error'
                        })
                    }
            })
            .catch(err => {
                    this.$Message({
                    showClose: true,
                    message:this.$t('text.failure') + err,
                    type: 'error'
                });
            })
            },

            /**
			 * 修改服务调用
             */
            userEdit() {
                this.$service.put('/system/users', this.data)
                        .then(r => {
                    if (r.data.code === this.$successCode) {
                        this.$emit('closeDialog', false)
                        this.$emit('loadData')
                        this.$Message({
                            showClose: true,
                            message: this.$t('text.success'),
                            type: 'success'
                        })
                    }else{
                        this.$Message({
                            showClose: true,
                            message: this.$t('text.failure')+r.data.msg,
                            type: 'error'
                        })
                    }
            })
            .catch(err => {
                    this.$Message({
                    showClose: true,
                    message: this.$t('text.failure') + err,
                    type: 'error'
                });
            })
            },
        }
    }
</script>

<style scoped>

</style>
