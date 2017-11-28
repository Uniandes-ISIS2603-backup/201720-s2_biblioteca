(function (ng) {
    // Definición del módulo
    var mod = ng.module("reservaModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/reserva/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            //$urlRouterProvider.otherwise("/prestamoList");
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('reservas', {
                url: '/reservas',
                abstract: true,
                parent: 'usuarioDetail',
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'reservas.html'                        
                    }
                }
            }).state('reservaList', {
                url: '/list',
                parent: 'reservas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'reservas.list.html',
                        controller: 'reservaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('reservaDelete', {
                url: '/{reservaId:int}/delete',
                parent: 'reservas',
                views: {
                    'detailView': {
                        templateUrl: 'src/modules/reserva/delete/reserva.delete.html',
                        controller: 'reservaDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('reservaCreate', {
                url: '/create',
                parent: 'reservas',
                views: {
                    'detailView': {
                        templateUrl: 'src/modules/reserva/new/reserva.new.html',
                        controller: 'reservaNewCtrl'
                    }
                }
            }).state('reservaDetail', {
                url: '/{reservaId:int}/detail',
                parent: 'reservas',
                views: {
                     'listView': {
                        templateUrl: basePath + 'reservas.list.html',
                        controller: 'reservaCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView': {
                        templateUrl: basePath + 'reservas.detail.html',
                        controller: 'reservaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('reservaAgregarLibros', {
                url: '/{reservaId:int}/agregarLibros',
                parent: 'reservas',
                views: {
                    'detailView': {
                        templateUrl: 'src/modules/reserva/agregarLibros/reserva.agregarLibros.html',
                        controller: 'reservaAgregarLibrosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('reservaAgregarVideos', {
                url: '/{reservaId:int}/agregarVideos',
                parent: 'reservas',
                views: {
                    'detailView': {
                        templateUrl: 'src/modules/reserva/agregarVideos/reserva.agregarVideos.html',
                        controller: 'reservaAgregarVideosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('reservaUpdate', {
                url: '/{reservaId:int}/update',
                parent: 'reservas',
                views: {
                    'detailView': {
                        templateUrl: 'src/modules/reserva/update/reserva.update.html',
                        controller: 'reservaUpdateCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            ;
        }
    ]);
})(window.angular);