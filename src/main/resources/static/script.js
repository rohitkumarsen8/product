$(document).ready(function() {
    const apiUrl = '/api/products';

    function loadProducts() {
        $.get(apiUrl, function(products) {
            const tbody = $('#productList tbody');
            tbody.empty();
            products.forEach(product => {
                tbody.append(`
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.name}</td>
                        <td>${product.description}</td>
                        <td>${product.price}</td>
                        <td>${product.quantity}</td>
                        <td>
                            <button class="edit-product" data-id="${product.id}">Edit</button>
                            <button class="delete-product" data-id="${product.id}">Delete</button>
                        </td>
                    </tr>
                `);
            });

            $('.edit-product').click(function() {
                const id = $(this).data('id');
                $.get(`${apiUrl}/${id}`, function(product) {
                    $('#productId').val(product.id);
                    $('#productName').val(product.name);
                    $('#productDescription').val(product.description);
                    $('#productPrice').val(product.price);
                    $('#productQuantity').val(product.quantity);
                });
            });

            $('.delete-product').click(function() {
                const id = $(this).data('id');
                $.ajax({
                    url: `${apiUrl}/${id}`,
                    type: 'DELETE',
                    success: function() {
                        loadProducts();
                    }
                });
            });
        });
    }

    $('#productForm').on('submit', function(e) {
        e.preventDefault();
        const product = {
            id: $('#productId').val(),
            name: $('#productName').val(),
            description: $('#productDescription').val(),
            price: $('#productPrice').val(),
            quantity: $('#productQuantity').val()
        };
        const method = product.id ? 'PUT' : 'POST';
        const url = product.id ? `${apiUrl}/${product.id}` : apiUrl;
        $.ajax({
            url: url,
            type: method,
            contentType: 'application/json',
            data: JSON.stringify(product),
            success: function() {
                $('#productForm')[0].reset();
                loadProducts();
            }
        });
    });

    $('#loadProducts').click(function() {
        loadProducts();
    });

    loadProducts();
});
