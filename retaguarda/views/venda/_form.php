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

    <?= $form->field($model, 'data')->widget(DateControl::classname(), [
    	'type'=>DateControl::FORMAT_DATETIME,
   	 	'ajaxConversion'=>false,
    	'options' => [
        	'pluginOptions' => [
            	'autoclose' => true
       		 ]
    	],
	]) ?>

    <?= $form->field($model, 'valor')->widget(MaskMoney::classname(), [
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
