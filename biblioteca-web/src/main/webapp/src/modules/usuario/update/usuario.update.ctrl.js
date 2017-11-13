(
        function (ng) {
            var mod = ng.module("usuarioModule");
            mod.constant("usuarioContext", "api/999/usuarios");
            mod.constant("prestamoContext", "api/books");
            mod.controller('usuarioUpdateCtrl', ['$scope', '$http', 'usuariosContext', '$state', 'prestamoContext', '$rootScope', '$filter',
                function ($scope, $http, usuarioContext, $state, prestamoContext, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idUsuario = $state.params.usuarioId;

                    //Consulto el autor a editar.
                    console.info(usuarioContext+'/' + idUsuario);
                        $http.get(usuarioContext + '/' + idUsuario).then(function (response) {
                        var usuario = response.data;
                        $scope.usuarioName = usuario.name;
                        $scope.usuarioTelefono = usuario.telefono;
                        $scope.usuarioDireccion = usuario.direccion;
                    });        
                    

                    $scope.createUsuario = function () {                   
                        $http.put(usuarioContext + "/" + idUsuario, {
                            name: $scope.usuarioName,
                            telefono: $scope.usuarioTelefono,
                            direccion: $scope.usuarioDireccion
                        }).then(function (response) {                            
                            $state.go('usuarioList', {usuarioId: response.data.id}, {reload: true});
                        });
                    };                   
                }
            ]);
        }
)(angular);