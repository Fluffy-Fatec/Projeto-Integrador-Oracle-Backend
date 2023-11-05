from datetime import datetime


class ETLTransformer:
    def __init__(self, column_types):
        self.column_types = column_types

    def convert_to_date(self, value):
        if isinstance(value, datetime):  # Check if the value is already a datetime object
            return value

        # Define the date formats to be attempted
        date_formats = ['%Y-%m-%d %H:%M:%S', '%d%m%Y%H%M%S']

        for date_format in date_formats:
            try:
                date_object = datetime.strptime(value, date_format)
                return date_object
            except ValueError:
                pass

        return None  # Return None if no valid date format is found

    def transform_data(self, data):
        transformed_data = []

        for row in data:
            transformed_row = []

            for i, value in enumerate(row):
                target_type = self.column_types[i]['type']

                if target_type == 'string':
                    transformed_row.append(str(value))
                elif target_type == 'date':
                    date = self.convert_to_date(value)
                    transformed_row.append(date)
                else:
                    transformed_row.append(value)

            transformed_data.append(transformed_row)

        return transformed_data
