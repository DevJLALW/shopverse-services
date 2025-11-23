CREATE TABLE t_order (
                         id BIGSERIAL PRIMARY KEY,
                         order_number VARCHAR(255)
);

CREATE TABLE t_order_line_items (
                                    id BIGSERIAL PRIMARY KEY,
                                    sku_code VARCHAR(255),
                                    price NUMERIC(19,2),
                                    quantity INTEGER,
                                    order_id BIGINT REFERENCES t_order(id)
);
