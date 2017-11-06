(function (ng) {
    var mod = ng.module("reservaModule");
    mod.constant("reservaContext", "reservas");
    mod.constant("usuarioContext","api/999/usuarios");
    mod.controller('reservaCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            if ($state.params.usuarioId !== undefined) {
                $http.get('api/999/usuarios/' + $state.params.usuarioId+'/reservas').then(function (response) {
                    $scope.reservaRecords = response.data;                    
                });
            }
            
            if ($state.params.reservaId !== undefined) {
                $http.get('api/999/usuarios/' + $state.params.usuarioId+'/reservas/'+$state.params.reservaId).then(function (response) {
                    $scope.reservaRecords = response.data.reservas;
                    $scope.currentReserva = response.data;
                    $scope.currentReservaLibros=response.data.libros;
                    $scope.currentReservaVideos=response.data.videos;
                });
            }       
            
            $scope.devolverLibroreserva = function (laId) {
                $http.put('api/999/libros/'+laId+'/devolverreserva', {

                }).then(function (response) {
                    //reserva created successfully
                    $state.go('reservaList', {reservaId: response.data.id}, {reload: true});
                });
            };
            
        }
    ]);
}
)(angular);