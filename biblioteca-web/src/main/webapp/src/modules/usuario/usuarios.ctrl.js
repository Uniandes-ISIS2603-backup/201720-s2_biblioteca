(function (ng) {
    var mod = ng.module("usuarioModule");
    mod.constant("usuarioContext", "api/usuarios");
    mod.controller('usuarioCtrl', ['$scope', '$http', 'usuarioContext',
        function ($scope, $http, usuarioContext) {
            $http.get('data/usuarios.json').then(function (response) {
                $scope.usuarioRecords = response.data;
            });
        }
    ]);
}
)(angular);