<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;
use yii\helpers\ArrayHelper;
use kartik\datecontrol\DateControl;
use kartik\money\MaskMoney;

/* @var $this yii\web\View */
/* @var $model app\models\Venda */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="venda-form">

    <?php $form = ActiveForm::begin(); ?>
    
    <?php $profileArray = ArrayHelper::map(\app\models\Profile::find()->orderBy('first_name')->all(), 'id',
    		function($model, $defaultValue) {
        		return $model->first_name.' '.$model->last_name.' - '.$model->cpf;
    })?>
    <?=	$form->field($model, 'profile_id')->dropDownList($profileArray, ['prompt' => '---- Selecione um Passageiro ----'])->label('Passageiro')?>

    <?= $form->field($model, 'data')->widget(DateControl::classname(), [
    	'type'=>DateControl::FORMAT_DATETIME,
   	 	'ajaxConversion'=>false,
    	'disabled'=>true,
    	'options' => [
        	'pluginOptions' => [
            	'autoclose' => true
       		 ]
    	],
	]) ?>

    <?= $form->field($model, 'valor')->widget(MaskMoney::classname(), [
    	'disabled'=>true,
    	'pluginOptions' => [
    	]
	])?>

    <?= $form->field($model, 'cartao_numero')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'validade')->textInput(['maxlength' => true]) ?>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Criar' : 'Atualizar', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
