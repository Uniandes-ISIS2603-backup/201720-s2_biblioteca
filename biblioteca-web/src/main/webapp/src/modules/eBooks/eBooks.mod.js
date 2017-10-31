(function (ng) {
    // Definición del módulo
    var mod = ng.module("eBookModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        // En basePath se encuentran los templates y controladores de módulo
        var basePath = 'src/modules/eBooks/';
        // Mostrar la lista de autores será el estado por defecto del módulo
        //$urlRouterProvider.otherwise("/eBooksList");
        // Definición del estado 'eBooksList' donde se listan los autores
        $stateProvider.state('eBooksList', {
            // Url que aparecerá en el browser
            url: '/eBooks/list',
            views: {
                'mainView': {
                    templateUrl: basePath + 'eBooks.list.html',
                    controller: 'eBookCtrl',
                    controllerAs: 'ctrl'
                }
            }
        });
    }
    ]);
})(window.angular);