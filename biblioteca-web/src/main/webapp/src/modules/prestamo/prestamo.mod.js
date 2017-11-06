(function (ng) {
    // Definición del módulo
    var mod = ng.module("prestamoModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/prestamo/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            //$urlRouterProvider.otherwise("/prestamoList");
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('prestamos', {
                url: '/prestamos',
                abstract: true,
                parent: 'usuarioDetail',
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'prestamos.html',                        
                    }
                }
            }).state('prestamoList', {
                url: '/list',
                parent: 'prestamos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'prestamos.list.html',
                        controller: 'prestamoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('prestamoDelete', {
                url: '/{prestamoId:int}/delete',
                parent: 'prestamos',
                views: {
                    'detailView': {
                        templateUrl: 'src/modules/prestamo/delete/prestamo.delete.html',
                        controller: 'prestamoDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('prestamoCreate', {
                url: '/create',
                parent: 'prestamos',
                views: {
                    'detailView': {
                        templateUrl: 'src/modules/prestamo/new/prestamo.new.html',
                        controller: 'prestamoNewCtrl'
                    }
                }
            }).state('prestamoDetail', {
                url: '/{prestamoId:int}/detail',
                parent: 'prestamos',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'prestamos.detail.html',
                        controller: 'prestamoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('prestamoUpdate', {
                url: '/{prestamoId:int}/update',
                parent: 'prestamos',
                views: {
                    'detailView': {
                        templateUrl: 'src/modules/prestamo/update/prestamo.update.html',
                        controller: 'prestamoUpdateCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('prestamoAgregarLibros', {
                url: '/{prestamoId:int}/agregarLibros',
                parent: 'prestamos',
                views: {
                    'detailView': {
                        templateUrl: 'src/modules/prestamo/agregarLibros/prestamo.agregarLibros.html',
                        controller: 'prestamoAgregarLibrosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            ;
        }
    ]);
})(window.angular);