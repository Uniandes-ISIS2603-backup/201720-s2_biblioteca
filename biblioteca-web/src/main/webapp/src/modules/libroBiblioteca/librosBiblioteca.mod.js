(function (ng) {
    // Definición del módulo
    var mod = ng.module("librosBibliotecaModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/libroBiblioteca/';
            // Mostrar la lista de autores será el estado por defecto del módulo
           // $urlRouterProvider.otherwise("/librosList");
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('librosBiblioteca', {
                // Url que aparecerá en el browser
                url: '/librosBiblioteca',
                abstract: true,
                 parent: 'bibliotecaDetail',
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'librosBiblioteca.html'
                     
                    }
                }
             }).state('librosBibliotecaList', {
                url: '/list',
                parent: 'librosBiblioteca',
                views: {
                    'listView': {
                        templateUrl: basePath + 'librosBiblioteca.list.html',
                         controller: 'librosBibliotecaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('librosBibliotecaCreate', {
                url: '/create',
                parent: 'librosBiblioteca',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/librosBiblioteca.new.html',
                        controller: 'libroBibliotecaNewCtrl'
                    }
                }
            });
        }]);
})(window.angular);
