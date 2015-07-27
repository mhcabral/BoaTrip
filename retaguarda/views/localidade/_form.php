<?php

use yii\helpers\Html;
use yii\helpers\ArrayHelper;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model app\models\Localidade */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="localidade-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'localidade_nome')->textInput(['maxlength' => true]) ?>

    <?php $ufArray = ArrayHelper::map(\app\models\Uf::find()->orderBy('uf_nome')->all(), 'id', 'uf_nome')?>
    <?=	$form->field($model, 'uf_id')->dropDownList($ufArray, ['prompt' => '---- Selecione a Unidade Federativa ----'])->label('Unidade Federativa')?>

    <?= $form->field($model, 'localidade_url')->textInput(['maxlength' => true]) ?>    

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Criar' : 'Atualizar', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
