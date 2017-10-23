(function (ng) {
    var mod = ng.module("bibliotecaModule");
    mod.constant("bibliotecasContext", "api/bibliotecas");
    mod.controller('bibliotecaCtrl', ['$scope', '$http', 'bibliotecasContext',
        function ($scope, $http, bibliotecasContext) {
            $http.get('data/bibliotecas.json').then(function (response) {
                $scope.bibliotecasRecords = response.data;
            });
        }
    ]);
}
)(angular);