package com.promise.platform.test.restdocs;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;

public class SkywalkerDocumentation
{
    public static final ArrayList<FieldDescriptor> basicResourceResponseSnippet = new ArrayList<FieldDescriptor>(
            Arrays.asList(
                    fieldWithPath("id").description("The ID of this resource."),
                    fieldWithPath("uri").description("The URI of this resource."),
                    fieldWithPath("name").description("The name of this resource."),
                    fieldWithPath("createdAt").description("The date when the resource created."),
                    fieldWithPath("updatedAt").description("The date when the resource last updated.")));

    public static final ArrayList<FieldDescriptor> scopedResourceResponseSnippet = new ArrayList<FieldDescriptor>(
            Arrays.asList(
                    fieldWithPath("id").description("The ID of this resource."),
                    fieldWithPath("uri").description("The URI of this resource."),
                    fieldWithPath("name").description("The name of this resource."),
                    fieldWithPath("createdAt").description("The date when the resource created."),
                    fieldWithPath("updatedAt").description("The date when the resource last updated."),
                    fieldWithPath("partitionUri").description("The URI of the resource's partition."),
                    fieldWithPath("scopes").description("The scopes of this resource.")));

    public static final ResponseFieldsSnippet getCollectionResponseSnippet = responseFields(
            fieldWithPath("pageIndex").description("This page index."),
            fieldWithPath("pageSize").description("This page size."),
            fieldWithPath("hasNext").description("If it has next page."),
            fieldWithPath("hasPrevious").description("If it has previous page."),
            fieldWithPath("nextPageUri").description("Next page URI if there is."),
            fieldWithPath("previousPageUri").description("Previous page URI if there is."),
            fieldWithPath("total").description("Total element count in all."),
            fieldWithPath("order").description("The sort order in this page."),
            fieldWithPath("orderBy").description("By what the sort order in this page."),
            subsectionWithPath("members").description("The element list in this page."));
}
