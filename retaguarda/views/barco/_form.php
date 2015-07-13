<?php

use yii\helpers\Html;
use yii\helpers\Url;
use yii\helpers\ArrayHelper;
use yii\widgets\ActiveForm;
use app\models\UploadForm;

/* @var $this yii\web\View */
/* @var $model app\models\Barco */
/* @var $form yii\widgets\ActiveForm */
?>



<div class="barco-form">

    <?php $form = ActiveForm::begin(['options' => ['enctype' => 'multipart/form-data']]); ?>

    <?= $form->field($model, 'nome')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'descricao')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'tripulantes')->textInput() ?>
    
    <?php $empresaArray = ArrayHelper::map(\app\models\Empresa::find()->orderBy('nome')->all(), 'id', 'nome')?>
    <?=	$form->field($model, 'empresa_id')->dropDownList($empresaArray, ['prompt' => '---- Selecione uma Empresa ----'])->label('Empresa')?>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>
    <div style="float: left; height: 50px; width: 350px;">
    	<?= $form->field($model, 'imageFile')->fileInput() ?>
    	
    </div>
    <div style="height: 50px; margin: 30px;">
    	
    </div>
    <?php ActiveForm::end(); ?>
        
</div>
    <table>
    	<caption><h1>Fotos</h1></caption>
    	<tbody>
    <?php  
	    if (($fotos = $model->barcoFotos) !== null) {
    		$cont = count($fotos);
    		$indice = 0;
    		foreach ($fotos as $foto) {
    			if ($indice == 0) {
    				echo '<tr>' . PHP_EOL;
    			}
    				echo '  <td><img src="'.$foto->foto_url.'" height="300" width="300">'.PHP_EOL;
    				//echo '    <a href="/index.php?r=barco%2Fdelete&amp;id=9" title="Delete" aria-label="Delete" data-confirm="Are you sure you want to delete this item?" data-method="post" data-pjax="0"><span class="glyphicon glyphicon-trash"></span></a></td>'.PHP_EOL;
    				//echo '    <a href="/index.php?r=barco/delete-foto&id='.$model->id.'&foto='.$foto->id.'" title="Apagar" aria-label="Delete" data-confirm="Você tem certeza que quer apagar essa foto?" data-method="post" data-pjax="0"><span class="glyphicon glyphicon-trash"></span></a></td>'.PHP_EOL;
    				echo Html::a('Remover Imagem',
    						Url::to(['barco-foto/delete', 'id'=>$foto->id]),
    						['class' => 'btn btn-danger', 'data-method'=>'post']
    				);
    		if ($indice == 3) {
    				echo '</tr>' . PHP_EOL;
    				$indice = 0;
    			} else $indice ++;
    		}
    	}
    ?>
    	</tbody>
    </table>
</div>
