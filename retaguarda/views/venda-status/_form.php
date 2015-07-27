<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model app\models\VendaStatus */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="venda-status-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'venda_nome')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'venda_valor')->textInput() ?>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Criar' : 'Atualizar', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
