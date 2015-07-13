<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model app\models\BarcoFoto */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="barco-foto-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'foto_url')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'barco_id')->textInput(['maxlength' => true]) ?>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
