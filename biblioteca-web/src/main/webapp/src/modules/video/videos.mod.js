(function (ng) {
    // Definición del módulo
    var mod = ng.module("videosModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/video/';
            // Mostrar la lista de videos será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/videosList");
            // Definición del estado 'videosList' donde se listan los videos
            $stateProvider.state('videos', {
                url: '/videos',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'videos.html',
                        controller: 'videosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('videosList', {
                url: '/list',
                parent: 'videos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'videos.list.html'
                    }
                }
            }).state('videosDetail', {
                url: '/{videosId:int}/detail',
                parent: 'videos',
                param: {
                    videosId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'videos.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'videos.detail.html',
                        controller: 'videosCtrl',
                        controllerAs: 'ctrl'
                    }

                }

            });
        }
    ]);
})(window.angular);