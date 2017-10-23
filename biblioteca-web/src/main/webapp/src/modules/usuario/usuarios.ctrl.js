(function (ng) {
    var mod = ng.module("usuarioModule");
    mod.constant("usuarioContext", "api/999/usuarios");
    mod.controller('usuarioCtrl', ['$scope', '$http', 'usuarioContext',
        function ($scope, $http, usuarioContext) {
            $http.get(usuarioContext).then(function (response) {
                $scope.usuarioRecords = response.data;
            });
        }
    ]);
}
)(angular);