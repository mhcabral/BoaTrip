<?php

use yii\helpers\Html;
use yii\helpers\ArrayHelper;
use yii\widgets\ActiveForm;
use kartik\datecontrol\DateControl;
use kartik\money\MaskMoney;

/* @var $this yii\web\View */
/* @var $model app\models\Passagem */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="passagem-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'quantidade')->textInput() ?>

    <?php $passagemTipoArray = ArrayHelper::map(\app\models\PassagemTipo::find()->orderBy('passagem_tipo_nome')->all(), 'id', 'passagem_tipo_nome')?>
    <?=	$form->field($model, 'passagem_tipo_id')->dropDownList($passagemTipoArray, ['prompt' => '---- Selecione um Tipo ----'])->label('Tipo')?>

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

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Criar' : 'Atualizar', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>
    
    

</div>
