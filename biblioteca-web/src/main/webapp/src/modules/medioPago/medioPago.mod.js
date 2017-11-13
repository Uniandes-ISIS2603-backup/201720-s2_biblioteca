(function (ng) {
    // Definición del módulo
    var mod = ng.module("medioPagoModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/medioPago/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            //$urlRouterProvider.otherwise("/medioPagoList");
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('medioPagos', {
                url: '/medioPagos',
                abstract: true,
                parent: 'usuarioDetail',
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'medioPago.html',                        
                    }
                }
            }).state('medioPagoList', {
                url: '/list',
                parent: 'medioPagos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'medioPago.list.html',
                        controller: 'medioPagoCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['admin', 'assistant']
                }
            }).state('medioPagoDelete', {
                url: '/{medioPagoId:int}/delete',
                parent: 'medioPagos',
                views: {
                    'detailView': {
                        templateUrl: 'src/modules/medioPago/delete/medioPago.delete.html',
                        controller: 'medioPagoDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['admin', 'assistant']
                }
            }).state('medioPagoCreate', {
                url: '/create',
                parent: 'medioPagos',
                views: {
                    'detailView': {
                        templateUrl: 'src/modules/medioPago/new/medioPago.new.html',
                        controller: 'medioPagoNewCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['admin', 'assistant']
                }
            }).state('medioPagoUpdate', {
                url: '/{medioPagoId:int}/update',
                parent: 'medioPagos',
                views: {
                    'detailView': {
                        templateUrl: 'src/modules/medioPago/update/medioPago.update.html',
                        controller: 'medioPagoUpdateCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['admin', 'assistant']
                }
            });
            ;
        }
    ]);
})(window.angular);