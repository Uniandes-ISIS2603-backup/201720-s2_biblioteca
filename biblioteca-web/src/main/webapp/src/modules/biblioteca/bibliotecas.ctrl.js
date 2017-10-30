(function (ng) {
    var mod = ng.module("bibliotecaModule");
    mod.constant("bibliotecaContext", "api/999/bibliotecas");
    mod.controller('bibliotecaCtrl', ['$scope', '$http', 'bibliotecaContext', '$state',
        function ($scope, $http, bibliotecaContext, $state) {
            $http.get(bibliotecaContext).then(function (response) {
                $scope.bibliotecasRecords = response.data;
            });
            
            if ($state.params.bibliotecaId !== undefined) {
                $http.get(bibliotecaContext + '/' + $state.params.bibliotecaId).then(function (response) {
                    $scope.bibliotecasRecords = response.data.biblioteca;
                    $scope.currentBiblioteca = response.data;
                });
            }
        }
    ]);
}
)(angular);