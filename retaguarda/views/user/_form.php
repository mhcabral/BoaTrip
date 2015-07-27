<?php

use yii\helpers\Html;
use yii\helpers\ArrayHelper;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model app\models\User */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="user-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'username')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'email')->textInput(['maxlength' => true]) ?>
    
    <?php $roleArray = ArrayHelper::map(\app\models\Role::find()->orderBy('role_name')->all(), 'id', 'role_name')?>
    <?=	$form->field($model, 'role_id')->dropDownList($roleArray, ['prompt' => '---- Selecione um Perfil ----'])->label('Perfil')?>
    
     <?php $statusArray = ArrayHelper::map(\app\models\Status::find()->orderBy('status_name')->all(), 'id', 'status_name')?>
    <?=	$form->field($model, 'status_id')->dropDownList($statusArray, ['prompt' => '---- Selecione um Status ----'])->label('Status')?>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Criar' : 'Atualizar', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
