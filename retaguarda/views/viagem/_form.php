<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;
use yii\helpers\ArrayHelper;
use kartik\datecontrol\DateControl;
use kartik\money\MaskMoney;

/* @var $this yii\web\View */
/* @var $model app\models\Viagem */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="viagem-form">

    <?php $form = ActiveForm::begin(); ?>
    
    <?= $form->field($model, 'data_saida')->widget(DateControl::classname(), [
    	'type'=>DateControl::FORMAT_DATETIME,
   	 	'ajaxConversion'=>false,
    	'options' => [
        	'pluginOptions' => [
            	'autoclose' => true
       		 ]
    	]
	]) ?>

    <?= $form->field($model, 'data_chegada')->widget(DateControl::classname(), [
    	'type'=>DateControl::FORMAT_DATETIME,
   	 	'ajaxConversion'=>false,
    	'options' => [
        	'pluginOptions' => [
            	'autoclose' => true
       		 ]
    	]
	]) ?>

    <?= $form->field($model, 'valor')->widget(MaskMoney::classname(), [
    	'pluginOptions' => [
    	]
	])?>
    

    <?= $form->field($model, 'valor_desconto')->widget(MaskMoney::classname(), [
    	'pluginOptions' => [
    	]
	])?>

    <?= $form->field($model, 'data_desconto_ini')->widget(DateControl::classname(), [
    	'type'=>DateControl::FORMAT_DATE,
   	 	'ajaxConversion'=>false,
    	'options' => [
        	'pluginOptions' => [
            	'autoclose' => true
       		 ]
    	]
	]) ?>
	
	<?= $form->field($model, 'data_desconto_fim')->widget(DateControl::classname(), [
    	'type'=>DateControl::FORMAT_DATE,
   	 	'ajaxConversion'=>false,
    	'options' => [
        	'pluginOptions' => [
            	'autoclose' => true
       		 ]
    	]
	]) ?>

    <?= $form->field($model, 'percurso')->textInput(['maxlength' => true]) ?>
    
    <?php $barcoArray = ArrayHelper::map(\app\models\Barco::find()->orderBy('nome')->all(), 'id', 'nome')?>
    <?=	$form->field($model, 'barco_id')->dropDownList($barcoArray, ['prompt' => '---- Selecione um Barco ----'])->label('Barco')?>

    <?php $origemArray = ArrayHelper::map(\app\models\Localidade::find()->orderBy('localidade_nome')->all(), 'id', 'localidade_nome')?>
    <?=	$form->field($model, 'localidade_origem')->dropDownList($origemArray, ['prompt' => '---- Selecione uma Localidade ----'])->label('Origem')?>

	<?php $destinoArray = ArrayHelper::map(\app\models\Localidade::find()->orderBy('localidade_nome')->all(), 'id', 'localidade_nome')?>
    <?=	$form->field($model, 'localidade_destino')->dropDownList($destinoArray, ['prompt' => '---- Selecione uma Localidade ----'])->label('Destino')?>
    
    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
