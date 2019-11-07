<template>
    <el-form :model="data" :rules="rules" ref="editForm" label-width="120px">
        <el-row>
<#list coder.fields as field>
            <sFormItem2 :label="$t('${coder.subModelName}.${field.propertyName}')" prop="${field.propertyName}">
                <el-input v-model="data.${field.propertyName}" :disabled="operator=='view'"></el-input>
            </sFormItem2>
</#list>
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
        name: "${coder.className}Edit",
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
				<#list coder.fields as field>
					${field.propertyName}: [
					<#if field.requiredFlag=="true">
	                 {required: true, message: this.$t('placeholder.default', [this.$t('${coder.subModelName}.${field.propertyName}')]), trigger: 'blur'}
					</#if>
                    ],
				</#list>
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
                this.$service.post('/${coder.modelName}/${coder.functionName}s', this.data)
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
                this.$service.put('/${coder.modelName}/${coder.functionName}s', this.data)
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
