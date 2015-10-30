<?php
use yii\helpers\Html;
use yii\bootstrap\Nav;
use yii\bootstrap\NavBar;
use yii\widgets\Breadcrumbs;
use app\assets\AppAsset;
//use app\widgets\Alert;
use yii\bootstrap\Alert;
use app\models\PermissionHelpers;
use app\assets\FontAwesomeAsset;

/* @var $this \yii\web\View */
/* @var $content string */

AppAsset::register($this);
FontAwesomeAsset::register($this);
?>
<?php $this->beginPage() ?>
<!DOCTYPE html>
<html lang="<?= Yii::$app->language ?>">
<head>
    <meta charset="<?= Yii::$app->charset ?>">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <?= Html::csrfMetaTags() ?>
    <title><?= Html::encode($this->title) ?></title>
    <?php $this->head() ?>
</head>
<body>

<?php $this->beginBody() ?>
    <div class="wrap">
        <?php
	        if (!Yii::$app->user->isGuest) {
	        	$is_admin = PermissionHelpers::requireMinimumRole('Admin');
	        	$is_empresa = PermissionHelpers::requireMinimumRole('Empresa');
	        	$is_passageiro = PermissionHelpers::requireMinimumRole('Passageiro');
	        }
	        NavBar::begin([
				'brandLabel' => 'Boatrip',
				'brandUrl' => Yii::$app->homeUrl,
				'options' => [
				'class' => 'navbar-inverse navbar-fixed-top',
				],
			]);
	        
	        $menuItems = [
	        		['label' => 'Home', 'url' => ['/site/index']],
	        		['label' => 'Sobre', 'url' => ['/site/about']],
	        		['label' => 'Contato', 'url' => ['/site/contact']],
	        		
	        ];
	        
	        
	        
	        if (!Yii::$app->user->isGuest && $is_admin) {
	        	$menuItems[] = ['label' => 'Usuários', 'url' => ['user/index']];
	        	$menuItems[] = ['label' => 'Perfil', 'url' => ['profile/index']];
	        	$menuItems[] = ['label' => 'Papeis', 'url' => ['role/index']];
	        }
	        if (Yii::$app->user->isGuest) {
	        	$menuItems[] = ['label' => 'Login', 'url' => ['site/login']];
	        	$menuItems[] = ['label' => 'Registrar-se', 'url' => ['/site/signup']];
	        } else {
	        	$menuItems[] = [
	        		'label' => 'Logout (' . Yii::$app->user->identity->username . ')',
	        		'url' => ['/site/logout'],
	        		'linkOptions' => ['data-method' => 'post']];
	        }
        	echo Nav::widget([
        		'options' => ['class' => 'navbar-nav navbar-right'],
        		'items' => $menuItems,]);
        	NavBar::end();        	
        	
        ?>
        
        <div class="container">
			<?= Breadcrumbs::widget([
				'links' => isset($this->params['breadcrumbs']) ?
				$this->params['breadcrumbs'] : [],
			])?>
			<?= $content ?>
		</div>
	</div>
	<footer class="footer">
		<div class="container">
			<p class="pull-left">&copy; Yii 2 Build <?= date('Y') ?></p>
			<p class="pull-right"><?= Yii::powered() ?></p>
		</div>
	</footer>

<?php $this->endBody() ?>
</body>
</html>
<?php $this->endPage() ?>
