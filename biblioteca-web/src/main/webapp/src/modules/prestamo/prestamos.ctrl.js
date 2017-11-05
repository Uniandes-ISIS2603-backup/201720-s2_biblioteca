(function (ng) {
    var mod = ng.module("prestamoModule");
    mod.constant("prestamoContext", "prestamos");
    mod.constant("usuarioContext","api/999/usuarios");
    mod.controller('prestamoCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            if ($state.params.usuarioId !== undefined) {
                $http.get('api/999/usuarios/' + $state.params.usuarioId+'/prestamos').then(function (response) {
                    $scope.prestamoRecords = response.data;                    
                });
            }
            
            if ($state.params.prestamoId !== undefined) {
                $http.get('api/999/usuarios/' + $state.params.usuarioId+'/prestamos/'+$state.params.prestamoId).then(function (response) {
                    $scope.prestamoRecords = response.data.prestamos;
                    $scope.currentPrestamo = response.data;
                    $scope.currentPrestamoLibros=response.data.libros;
                    $scope.currentPrestamoVideos=response.data.videos;
                });
            }       
            
            
        }
    ]);
}
)(angular);